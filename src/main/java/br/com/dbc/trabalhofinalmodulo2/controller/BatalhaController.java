package br.com.dbc.trabalhofinalmodulo2.controller;


import br.com.dbc.trabalhofinalmodulo2.exceptions.BancoDeDadosException;
import br.com.dbc.trabalhofinalmodulo2.interfaces.Atacar;
import br.com.dbc.trabalhofinalmodulo2.model.entities.Batalha;
import br.com.dbc.trabalhofinalmodulo2.model.entities.Boss;
import br.com.dbc.trabalhofinalmodulo2.model.entities.Cenario;
import br.com.dbc.trabalhofinalmodulo2.model.entities.Personagem;
import br.com.dbc.trabalhofinalmodulo2.service.BossService;
import br.com.dbc.trabalhofinalmodulo2.service.CenarioService;

import java.util.Date;
import java.util.Objects;
import java.util.Random;

public class BatalhaController implements Atacar {

    private Cenario cenario;
    private Personagem personagem = new Personagem();
    private Boss boss = new Boss();
    private int roundAtual = 0;

    public Cenario getCenario() {
        return cenario;
    }

    public void setCenario(Cenario cenario) {
        this.cenario = cenario;
    }

    public Personagem getPersonagem() {
        return personagem;
    }

    public void setPersonagem(Personagem personagem) {
        this.personagem = personagem;
    }

    public Boss getBoss() {
        return boss;
    }

    public void setBoss(Boss boss) {
        this.boss = boss;
    }


    public void setRoundAtual(int roundAtual) {
        this.roundAtual = roundAtual;
    }

    @Override
    public void atacar() {
        //ataque do jogador no boss
        Random random = new Random();
        int valor = random.nextInt(10);
        int atqJogador = this.personagem.getClassePersonagem().getAtaqueClasse();
        int vidaBoss = this.boss.getVida();
        int defesaBoss = this.boss.getDefesa();
        int valorAtq = atqJogador + valor;

        if (valor <= 2) {
            System.out.println("O Boss desviou do seu ataque");

        } else {
            System.out.println("Ataque bem sucedido, você causou " + getPersonagem().getClassePersonagem().getAtaqueClasse() + " de dano no boss");
            this.boss.setVida(vidaBoss + defesaBoss - valorAtq);
        }
    }

    public Batalha retornaBatalha(int idJogador,String status,Cenario cenario){
        return new Batalha(cenario.getIdCenario(), idJogador,this.getBoss().getIdBoss(),this.roundAtual,status);
    }

    public String imprimiStatusJogador() {
        String nome = getPersonagem().getClassePersonagem().getNomeClassePersonagem();
        int vida = getPersonagem().getClassePersonagem().getVidaClasse();
        int ataque = getPersonagem().getClassePersonagem().getAtaqueClasse();
        int defesa = getPersonagem().getClassePersonagem().getDefesaClasse();

        return "" +
                "| Personagem: " + nome +
                "\n| Vida: " + vida
                + "\n" +
                "| Ataque: " + ataque
                + "\n" +
                "| Defesa: " + defesa +
                "";
    }

    public String imprimiStatusBoss() {
        String nome = getBoss().getNome();
        int vida = getBoss().getVida();
        int ataque = getBoss().getAtaque();
        int defesa = getBoss().getDefesa();

        return "" +
                "| Boss: " + nome +
                "\n| Vida: " + vida
                + "\n" +
                "| Ataque: " + ataque
                + "\n" +
                "| Defesa: " + defesa
                + "\n" +
                "";
    }

    @Override
    public void ataqueBoss() {

        Random random = new Random();
        int valor = random.nextInt(10);
        int vidaJogador = this.personagem.getClassePersonagem().getVidaClasse();
        int atqBoss = this.boss.getAtaque();
        int defesaPorcento = this.personagem.getClassePersonagem().getDefesaClasse()/100;
        int valorAtq = atqBoss + valor - defesaPorcento;
        if (valor <= 3) {
            System.out.println("Você desviou do ataque do boss\n");
        } else {
            System.out.println("O boss causou " + getBoss().getAtaque() + " de dano no jogador\n");
            this.personagem.getClassePersonagem().setVidaClasse(vidaJogador - valorAtq);
        }
    }

    public void sortearCenario() throws BancoDeDadosException {

        if (this.personagem == null) {
            System.out.println("| Não há personagem disponível para a batalha |");
        }else {
            BossService bossService = new BossService();
            CenarioService cenarioService = new CenarioService();
            Random random = new Random();

            int sortear = random.nextInt(2) + 1;
            if (sortear == 1) {
                Boss boss = new Boss("Boss Da Luz");
                Cenario cenario = new Cenario("Cenario da luz", "1", new Date());
                bossService.adicionar(boss);
                cenarioService.adicionarCenario(cenario);
                setBoss(bossService.retornaBoss("Boss Da Luz"));
                setCenario(cenarioService.retornaCenario("1"));
            } else {
                Boss boss = new Boss("Boss Da Sombras");
                Cenario cenario = new Cenario("Cenario da sombras", "2", new Date());
                bossService.adicionar(boss);
                cenarioService.adicionarCenario(cenario);
                setBoss(bossService.retornaBoss("Boss Da Sombras"));
                setCenario(cenarioService.retornaCenario("2"));
            }
        }
    }

    public void inciarAtaque() {
        setRoundAtual(roundAtual++);
        System.out.println(this.imprimiStatusBoss());
        System.out.println(this.imprimiStatusJogador());
        this.atacar();
        this.ataqueBoss();
    }

    public void retornaBoss(){

        if (Objects.equals(this.boss.getNome(), "Boss Da Luz")) {

            System.out.println("""
                    --------------------
                    O BOSS DA LUZ ESTÁ CHEGANDO...
                    --------------------\040\040\040\040\040\040\040\040
                               /                            )
                              (                             |\\
                             /|                              \\\\
                            //                                \\\\
                           ///                                 \\|
                          /( \\                                  )\\
                          \\\\  \\_                               //)
                           \\\\  :\\__                           ///
                            \\\\     )                         // \\
                             \\\\:  /                         // |/
                              \\\\ / \\                       //  \\
                               /)   \\   ___..-'           (|  \\_|
                              //     /   _.'              \\ \\  \\
                             /|       \\ \\________          \\ | /
                            (| _ _  __/          '-.       ) /.'
                             \\\\ .  '-.__            \\_    / / \\
                              \\\\_'.     > --._ '.     \\  / / /
                               \\ \\      \\     \\  \\     .' /.'
                                \\ \\  '._ /     \\ )    / .' |
                                 \\ \\_     \\_   |    .'_/ __/
                                  \\  \\      \\_ |   / /  _/ \\_
                                   \\  \\       / _.' /  /     \\
                                   \\   |     /.'   / .'       '-,_
                                    \\   \\  .'   _.'_/             \\
                       /\\    /\\      ) ___(    /_.'           \\    |
                      | _\\__// \\    (.'      _/               |    |
                      \\/_  __  /--'`    ,                   __/    /
                      (_ ) /b)  \\  '.   :            \\___.-'_/ \\__/
                      /:/:  ,     ) :        (      /_.'__/-'|_ _ /
                     /:/: __/\\ >  __,_.----.__\\    /        (/(/(/
                    (_(,_/V .'/--'    _/  __/ |   /
                     VvvV  //`    _.-' _.'     \\   \\
                       n_n//     (((/->/        |   /
                       '--'         ~='          \\  |
                                                  | |_,,,
                                                  \\  \\  /
                                                   '.__)""");
        } else {

            System.out.println("""
                    --------------------
                    O BOSS DAS SOMBRAS ESTÁ CHEGANDO...
                    --------------------\040\040\040\040\040\040\040\040\040\040\040\040\040\040
                                                                       ,--,  ,.-.
                                   ,                   \\,       '-,-`,'-.' | ._
                                  /|           \\    ,   |\\         }  )/  / `-,',
                                  [ ,          |\\  /|   | |        /  \\|  |/`  ,`
                                  | |       ,.`  `,` `, | |  _,...(   (      .',
                                  \\  \\  __ ,-` `  ,  , `/ |,'      Y     (   /_L\\
                                   \\  \\_\\,``,   ` , ,  /  |         )         _,/
                                    \\  '  `  ,_ _`_,-,<._.<        /         /
                                     ', `>.,`  `  `   ,., |_      |         /
                                       \\/`  `,   `   ,`  | /__,.-`    _,   `\\
                                   -,-..\\  _  \\  `  /  ,  / `._) _,-\\`       \\
                                    \\_,,.)  \\    ` /  / ) (-,, ``    ,        |
                                   ,` )  | \\_\\       '-`  |  `(               \\
                                  /  /```(   , --, ,' \\   |`<`    ,            |
                                 /  /_,--`\\   <\\  V /> ,` )<_/)  | \\      _____)
                           ,-, ,`   `   (_,\\ \\    |   /) / __/  /   `----`
                          (-, \\           ) \\ ('_.-._)/ /,`    /
                          | /  `          `/ \\\\ V   V, /`     /
                       ,--\\(        ,     <_/`\\\\     ||      /
                      (   ,``-     \\/|         \\-A.A-`|     /
                     ,>,_ )_,..(    )\\          -,,_-`  _--`
                    (_ \\|`   _,/_  /  \\_            ,--`
                     \\( `   <.,../`     `-.._   _,-`""");

        }
    }

    public void retornaStatusVitoria(Boolean vitoria) {
        if (vitoria) {

            System.out.println("""
                    Parabéns! Você venceu e salvou o Reino!
                    ██╗   ██╗ ██████╗  ██████╗███████╗                     \s
                    ██║   ██║██╔═══██╗██╔════╝██╔════╝                     \s
                    ██║   ██║██║   ██║██║     █████╗                       \s
                    ╚██╗ ██╔╝██║   ██║██║     ██╔══╝                       \s
                     ╚████╔╝ ╚██████╔╝╚██████╗███████╗                     \s
                      ╚═══╝   ╚═════╝  ╚═════╝╚══════╝                     \s
                        ██╗   ██╗███████╗███╗   ██╗ ██████╗███████╗██╗   ██╗
                        ██║   ██║██╔════╝████╗  ██║██╔════╝██╔════╝██║   ██║
                        ██║   ██║█████╗  ██╔██╗ ██║██║     █████╗  ██║   ██║
                        ╚██╗ ██╔╝██╔══╝  ██║╚██╗██║██║     ██╔══╝  ██║   ██║
                         ╚████╔╝ ███████╗██║ ╚████║╚██████╗███████╗╚██████╔""");

        } else {
            System.out.println("""
                    O Reino continua em perigo...
                     ██▒   █▓ ▒█████   ▄████▄  ▓█████               \s
                    ▓██░   █▒▒██▒  ██▒▒██▀ ▀█  ▓█   ▀               \s
                     ▓██  █▒░▒██░  ██▒▒▓█    ▄ ▒███                 \s
                      ▒██ █░░▒██   ██░▒▓▓▄ ▄██▒▒▓█  ▄               \s
                       ▒▀█░  ░ ████▓▒░▒ ▓███▀ ░░▒████▒              \s
                       ░ ▐░  ░ ▒░▒░▒░ ░ ░▒ ▒  ░░░ ▒░ ░              \s
                       ░ ░░    ░ ▒ ▒░   ░  ▒    ░ ░  ░              \s
                         ░░  ░ ░ ░ ▒  ░           ░                 \s
                          ░      ░ ░  ░ ░         ░  ░              \s
                     ██▓███  ▓█████  ██▀███  ▓█████▄ ▓█████  █    ██\s
                    ▓██░  ██▒▓█   ▀ ▓██ ▒ ██▒▒██▀ ██▌▓█   ▀  ██  ▓██▒
                    ▓██░ ██▓▒▒███   ▓██ ░▄█ ▒░██   █▌▒███   ▓██  ▒██░
                    ▒██▄█▓▒ ▒▒▓█  ▄ ▒██▀▀█▄  ░▓█▄   ▌▒▓█  ▄ ▓▓█  ░██░
                    ▒██▒ ░  ░░▒████▒░██▓ ▒██▒░▒████▓ ░▒████▒▒▒█████▓\s
                    ▒▓▒░ ░  ░░░ ▒░ ░░ ▒▓ ░▒▓░ ▒▒▓  ▒ ░░ ▒░ ░░▒▓▒ ▒ ▒\s
                    ░▒ ░      ░ ░  ░  ░▒ ░ ▒░ ░ ▒  ▒  ░ ░  ░░░▒░ ░ ░\s
                    ░░          ░     ░░   ░  ░ ░  ░    ░    ░░░ ░ ░\s
                                ░  ░   ░        ░       ░  ░   ░    \s
                                              ░                    \s""");
        }
    }
}
