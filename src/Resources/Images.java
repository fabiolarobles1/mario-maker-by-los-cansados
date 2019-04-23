package Resources;

import javax.imageio.ImageIO;
import javax.swing.*;

import Game.Entities.DynamicEntities.Player;
import Game.Entities.StaticEntities.BreakBlock;
import Game.GameStates.State;
import Game.World.Map;
import Game.World.MapBuilder;
import Main.Handler;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;

/**
 * Created by AlexVR on 7/1/2018.
 */
public class Images {

	
    public static BufferedImage[] butstart;
    public static BufferedImage[] backgrounds;
    public static BufferedImage[] backgrounds2;

    public static BufferedImage[] marioSmallWalkLeft;
    public static BufferedImage[] marioSmallWalkRight;
    public static BufferedImage[] marioSmallJumpLeft;//also store ide sprites like looking up or down
    public static BufferedImage[] marioSmallJumpRight;
    
    public static BufferedImage[] luigiSmallWalkLeft;
    public static BufferedImage[] luigiSmallWalkRight;
    public static BufferedImage[] luigiSmallJumpLeft;//also store ide sprites like looking up or down
    public static BufferedImage[] luigiSmallJumpRight;
    
    public static BufferedImage[] warioSmallWalkLeft;
    public static BufferedImage[] warioSmallWalkRight;
    public static BufferedImage[] warioSmallJumpLeft;//also store ide sprites like looking up or down
    public static BufferedImage[] warioSmallJumpRight;
    
    public static BufferedImage[] yoshiSmallWalkLeft;
    public static BufferedImage[] yoshiSmallWalkRight;
    public static BufferedImage[] yoshiSmallJumpLeft;//also store ide sprites like looking up or down
    public static BufferedImage[] yoshiSmallJumpRight;
    
    public static BufferedImage[] yoshiSmallJumpLeftextra;
    public static BufferedImage[] yoshiSmallJumpRightextra;

    public static BufferedImage[] marioBigWalkLeft;
    public static BufferedImage[] marioBigWalkRight;
    public static BufferedImage[] marioBigRunLeft;
    public static BufferedImage[] marioBigRunRight;
    public static BufferedImage[] marioBigJumpLeft;//also store ide sprites like looking up or down and left<->right transition
    public static BufferedImage[] marioBigJumpRight;
    
    public static BufferedImage[] luigiBigWalkLeft;
    public static BufferedImage[] luigiBigWalkRight;
    public static BufferedImage[] luigiBigRunLeft;
    public static BufferedImage[] luigiBigRunRight;
    public static BufferedImage[] luigiBigJumpLeft;//also store ide sprites like looking up or down and left<->right transition
    public static BufferedImage[] luigiBigJumpRight;
    
    public static BufferedImage[] warioBigWalkLeft;
    public static BufferedImage[] warioBigWalkRight;
    public static BufferedImage[] warioBigRunLeft;
    public static BufferedImage[] warioBigRunRight;
    public static BufferedImage[] warioBigJumpLeft;//also store ide sprites like looking up or down and left<->right transition
    public static BufferedImage[] warioBigJumpRight;
    
    public static BufferedImage[] yoshiBigWalkLeft;
    public static BufferedImage[] yoshiBigWalkRight;
    public static BufferedImage[] yoshiBigRunLeft;
    public static BufferedImage[] yoshiBigRunRight;
    public static BufferedImage[] yoshiBigJumpLeft;//also store ide sprites like looking up or down and left<->right transition
    public static BufferedImage[] yoshiBigJumpRight;
    
    public static BufferedImage[] item;
    public static BufferedImage[] enemy;
    public static BufferedImage[] hitWall;
    public static BufferedImage[] enemyFG;
    public static BufferedImage[] enemyGB1;
    public static BufferedImage[] enemyGB2;
    public static BufferedImage[] enemyGB3;
    public static BufferedImage enemyBL;
    public static BufferedImage enemyPS;
    public static BufferedImage enemyHT;
    public static BufferedImage enemySmash;


    public static BufferedImage[] goomba;
    public static BufferedImage deathBlock;


    public static BufferedImage title;
    public static BufferedImage Pause;
    public static BufferedImage MenuBorder;
    public static BufferedImage[] Over;
    public static BufferedImage Cursor;

    public static BufferedImage testMap;
    public static BufferedImage testMapMultiplayer;
    public static BufferedImage testMaptwo;
    public static BufferedImage testMaptwoMultiplayer;
    public static BufferedImage helloworldMap;
    public static BufferedImage helloworld_mult_Map;
    public static BufferedImage marioAndLuigiRace;
    public static BufferedImage marioRaceSolo;

    public static BufferedImage breakBlock;
    public static BufferedImage misteryBlock;
    public static BufferedImage[] rotatingmisteryBlock;
    public static BufferedImage surfaceBlock;
    public static BufferedImage alternateSurfaceBlock;
    public static BufferedImage boundBlock;
    public static BufferedImage finishBlock;
    public static BufferedImage mushroom;
    public static BufferedImage[] floatingBlock;
    public static BufferedImage cloud;
    public static BufferedImage[] coin;
    public static BufferedImage goombaDies;
    public static BufferedImage[] Lannoyingpest;
    public static BufferedImage[] Rannoyingpest;

    private SpriteSheet mainmenuSpriteSheet;
    private SpriteSheet gameoverSpriteSheet;
    private SpriteSheet backgroundSpriteSheet;
    private SpriteSheet interactableSpriteSheet;
    private SpriteSheet player1SpriteSheet;
    private SpriteSheet player2SpriteSheet;
    private SpriteSheet player1SpriteSheet2;
    private SpriteSheet player2SpriteSheet2Small;
    private SpriteSheet player2SpriteSheet2Big;
    
    private SpriteSheet blockSpriteSheet;
    private SpriteSheet goombaSpriteSheet;
    private SpriteSheet enemySheet;
    private SpriteSheet SSpriteSheet;
    private SpriteSheet SAttackSpriteSheet;
    private SpriteSheet floatingblockSheet;
    private SpriteSheet annoyingpestSheet;

    
    
    
    
    
    public Images() {

    	
    	
        butstart = new BufferedImage[3];

        Over = new BufferedImage[2];
        
        backgrounds = new BufferedImage[9];
        backgrounds2 = new BufferedImage[6];

        marioSmallWalkLeft = new BufferedImage[2];
        marioSmallWalkRight = new BufferedImage[2];
        marioSmallJumpLeft = new BufferedImage[4];
        marioSmallJumpRight = new BufferedImage[4];
        
        luigiSmallWalkLeft = new BufferedImage[2];
        luigiSmallWalkRight = new BufferedImage[2];
        luigiSmallJumpLeft = new BufferedImage[4];
        luigiSmallJumpRight = new BufferedImage[4];
        
        warioSmallWalkLeft = new BufferedImage[2];
        warioSmallWalkRight = new BufferedImage[2];
        warioSmallJumpLeft = new BufferedImage[4];
        warioSmallJumpRight = new BufferedImage[4];
        
        yoshiSmallWalkLeft = new BufferedImage[2];
        yoshiSmallWalkRight = new BufferedImage[2];
        yoshiSmallJumpLeft = new BufferedImage[4];
        yoshiSmallJumpRight = new BufferedImage[4];
        
        yoshiSmallJumpLeftextra = new BufferedImage[8];
        yoshiSmallJumpRightextra = new BufferedImage[8];
        
        item = new BufferedImage[19];
        enemy = new BufferedImage[9];
        hitWall = new BufferedImage[44];
        enemyFG = new BufferedImage[9];
        enemyGB1 = new BufferedImage[3];
        enemyGB2 = new BufferedImage[2];
        enemyGB3 = new BufferedImage[3];


        marioBigWalkLeft = new BufferedImage[2];
        marioBigWalkRight = new BufferedImage[2];
        marioBigRunLeft = new BufferedImage[2];
        marioBigRunRight = new BufferedImage[2];
        marioBigJumpLeft = new BufferedImage[5];
        marioBigJumpRight = new BufferedImage[5];
        
        luigiBigWalkLeft = new BufferedImage[2];
        luigiBigWalkRight = new BufferedImage[2];
        luigiBigRunLeft = new BufferedImage[2];
        luigiBigRunRight = new BufferedImage[2];
        luigiBigJumpLeft = new BufferedImage[5];
        luigiBigJumpRight = new BufferedImage[5];
        
        warioBigWalkLeft = new BufferedImage[2];
        warioBigWalkRight = new BufferedImage[2];
        warioBigRunLeft = new BufferedImage[2];
        warioBigRunRight = new BufferedImage[2];
        warioBigJumpLeft = new BufferedImage[5];
        warioBigJumpRight = new BufferedImage[5];
        
        warioBigWalkLeft = new BufferedImage[2];
        warioBigWalkRight = new BufferedImage[2];
        warioBigRunLeft = new BufferedImage[2];
        warioBigRunRight = new BufferedImage[2];
        warioBigJumpLeft = new BufferedImage[5];
        warioBigJumpRight = new BufferedImage[5];
        
        yoshiBigWalkLeft = new BufferedImage[2];
        yoshiBigWalkRight = new BufferedImage[2];
        yoshiBigRunLeft = new BufferedImage[2];
        yoshiBigRunRight = new BufferedImage[2];
        yoshiBigJumpLeft = new BufferedImage[5];
        yoshiBigJumpRight = new BufferedImage[5];
        
        
        
        goomba = new BufferedImage[2];
        
        floatingBlock = new BufferedImage[2];

        rotatingmisteryBlock = new BufferedImage[4];
        
        Lannoyingpest = new BufferedImage[4];
        Rannoyingpest = new BufferedImage[4];
        
        coin = new BufferedImage[4];


        try {


            //spriteSheets
            mainmenuSpriteSheet = new SpriteSheet(ImageIO.read(getClass().getResourceAsStream("/Sheets/mainmenuSheet.png")));
            gameoverSpriteSheet = new SpriteSheet(ImageIO.read(getClass().getResourceAsStream("/Sheets/gameoverSheet.png")));
            backgroundSpriteSheet = new SpriteSheet(ImageIO.read(getClass().getResourceAsStream("/Sheets/backgroundSheet.png")));
            interactableSpriteSheet = new SpriteSheet(ImageIO.read(getClass().getResourceAsStream("/Sheets/interactablesSheet.png")));
            player1SpriteSheet = new SpriteSheet(ImageIO.read(getClass().getResourceAsStream("/Sheets/marioSNESSheet.png")));
            player2SpriteSheet = new SpriteSheet(ImageIO.read(getClass().getResourceAsStream("/Sheets/luigiSNESSheet.png")));
            player1SpriteSheet2 = new SpriteSheet(ImageIO.read(getClass().getResourceAsStream("/Sheets/wario.png")));
            player2SpriteSheet2Small = new SpriteSheet(ImageIO.read(getClass().getResourceAsStream("/Sheets/yoshiSmallSprite.png")));
            player2SpriteSheet2Big = new SpriteSheet(ImageIO.read(getClass().getResourceAsStream("/Sheets/yoshiBigSprite.png")));
            blockSpriteSheet = new SpriteSheet(ImageIO.read(getClass().getResourceAsStream("/Sheets/blocksSheet.png")));
            goombaSpriteSheet = new SpriteSheet(ImageIO.read(getClass().getResourceAsStream("/Sheets/goombaSprite.png")));
            SSpriteSheet = new SpriteSheet(ImageIO.read(getClass().getResourceAsStream("/Sheets/Sheets.png")));
            SAttackSpriteSheet = new SpriteSheet(ImageIO.read(getClass().getResourceAsStream("/Sheets/enemySheet2.png")));
            enemySheet= new SpriteSheet(ImageIO.read(getClass().getResourceAsStream("/Sheets/enemySheet.png")));
            floatingblockSheet = new SpriteSheet(ImageIO.read(getClass().getResourceAsStream("/Sheets/floatingblock.png")));
            annoyingpestSheet = new SpriteSheet(ImageIO.read(getClass().getResourceAsStream("/Sheets/anoyingpest.png")));
            
            //Images
            title = mainmenuSpriteSheet.crop(16,16,256,224);
            Pause = ImageIO.read(getClass().getResourceAsStream("/Sheets/Pause.png"));
            MenuBorder = ImageIO.read(getClass().getResourceAsStream("/Sheets/menuBorder.png"));
            Cursor = ImageIO.read(getClass().getResourceAsStream("/Sheets/cursor.png"));
            butstart[0]= ImageIO.read(getClass().getResourceAsStream("/Buttons/NormBut.png"));//normbut
            butstart[1]= ImageIO.read(getClass().getResourceAsStream("/Buttons/HoverBut.png"));//hoverbut
            butstart[2]= ImageIO.read(getClass().getResourceAsStream("/Buttons/ClickedBut.png"));//clickbut
            
            Over[0] = gameoverSpriteSheet.crop(5, 7, 256, 224);
            Over[1] = gameoverSpriteSheet.crop(528, 7, 256, 224);

            backgrounds[0] = backgroundSpriteSheet.crop(2,2,512,432);
            backgrounds[1] = backgroundSpriteSheet.crop(516,2,512,432);
            backgrounds[2] = backgroundSpriteSheet.crop(2,438,512,432);
            backgrounds[3] = backgroundSpriteSheet.crop(516,438,512,432);
            backgrounds[4] = backgroundSpriteSheet.crop(2,872,512,432);
            backgrounds[5] = backgroundSpriteSheet.crop(516,872,512,432);
            backgrounds[6] = backgroundSpriteSheet.crop(2,1306,512,432);
            backgrounds[7] = backgroundSpriteSheet.crop(516,1306,512,432);
            backgrounds[8] = backgroundSpriteSheet.crop(2,1740,512,432);
            
            backgrounds2[0] = ImageIO.read(getClass().getResourceAsStream("/Sheets/GrasslandBackground.png"));
            backgrounds2[1] = ImageIO.read(getClass().getResourceAsStream("/Sheets/DarkBackground.png"));
            backgrounds2[2] = ImageIO.read(getClass().getResourceAsStream("/Sheets/CanyonBackground.png"));
            backgrounds2[3] = ImageIO.read(getClass().getResourceAsStream("/Sheets/DesertBackground.png"));
            backgrounds2[4] = ImageIO.read(getClass().getResourceAsStream("/Sheets/Snow1Background.png"));
            backgrounds2[5] = ImageIO.read(getClass().getResourceAsStream("/Sheets/Snow2Background.png"));


            //player sprites
            //Small
            marioSmallWalkLeft[0] = player1SpriteSheet.crop(169,0,14,20);
            marioSmallWalkLeft[1] = player1SpriteSheet.crop(49,0,15,19);

            marioSmallWalkRight[0] = player1SpriteSheet.crop(209,0,14,20);
            marioSmallWalkRight[1] = player1SpriteSheet.crop(328,0,15,19);

            marioSmallJumpLeft[0] = player1SpriteSheet.crop(168,39,16,22);
            marioSmallJumpLeft[1] = player1SpriteSheet.crop(128,40,16,20);
            marioSmallJumpLeft[2] = player1SpriteSheet.crop(49,39,15,21);//up
            marioSmallJumpLeft[3] = player1SpriteSheet.crop(89,43,15,14);//down

            marioSmallJumpRight[0] = player1SpriteSheet.crop(208,39,16,22);
            marioSmallJumpRight[1] = player1SpriteSheet.crop(248,40,16,20);
            marioSmallJumpRight[2] = player1SpriteSheet.crop(328,39,15,21);//up
            marioSmallJumpRight[3] = player1SpriteSheet.crop(288,43,15,14);//down

            // Big
            marioBigWalkLeft[0] = player1SpriteSheet.crop(169,76,15,28);
            marioBigWalkLeft[1] = player1SpriteSheet.crop(8,76,16,28);

            marioBigWalkRight[0] = player1SpriteSheet.crop(208,76,15,28);
            marioBigWalkRight[1] = player1SpriteSheet.crop(368,76,16,28);

            marioBigRunLeft[0] = player1SpriteSheet.crop(169,76,15,28);
            marioBigRunLeft[1] = player1SpriteSheet.crop(48,76,16,27);

            marioBigRunRight[0] = player1SpriteSheet.crop(208,76,15,28);
            marioBigRunRight[1] = player1SpriteSheet.crop(328,76,16,27);

            marioBigJumpLeft[0] = player1SpriteSheet.crop(168,114,16,31);
            marioBigJumpLeft[1] = player1SpriteSheet.crop(128,115,16,29);
            marioBigJumpLeft[2] = player1SpriteSheet.crop(129,196,15,27);//up
            marioBigJumpLeft[3] = player1SpriteSheet.crop(88,122,16,15);//down
            marioBigJumpLeft[4] = player1SpriteSheet.crop(8,115,16,29);//change

            marioBigJumpRight[0] = player1SpriteSheet.crop(208,114,16,31);
            marioBigJumpRight[1] = player1SpriteSheet.crop(248,115,16,29);
            marioBigJumpRight[2] = player1SpriteSheet.crop(248,196,15,27);//up
            marioBigJumpRight[3] = player1SpriteSheet.crop(287,122,16,15);//down
            marioBigJumpRight[4] = player1SpriteSheet.crop(368,115,16,29);//Change
            
            //player 2 sprites
            //Small
            luigiSmallWalkLeft[0] = player2SpriteSheet.crop(199,0,14,22);
            luigiSmallWalkLeft[1] = player2SpriteSheet.crop(169,0,15,21);

            luigiSmallWalkRight[0] = player2SpriteSheet.crop(235,0,14,22);
            luigiSmallWalkRight[1] = player2SpriteSheet.crop(264,0,15,21);

            luigiSmallJumpLeft[0] = player2SpriteSheet.crop(199,30,15,21);
            luigiSmallJumpLeft[1] = player2SpriteSheet.crop(168,30,16,22);
            luigiSmallJumpLeft[2] = player2SpriteSheet.crop(85,30,15,22);//up
            luigiSmallJumpLeft[3] = player2SpriteSheet.crop(112,34,15,14);//down

            luigiSmallJumpRight[0] = player2SpriteSheet.crop(234,30,15,21);
            luigiSmallJumpRight[1] = player2SpriteSheet.crop(264,30,16,22);
            luigiSmallJumpRight[2] = player2SpriteSheet.crop(348,30,15,22);//up
            luigiSmallJumpRight[3] = player2SpriteSheet.crop(321,34,15,14);//down

            // Big
            luigiBigWalkLeft[0] = player2SpriteSheet.crop(199,116,15,30);
            luigiBigWalkLeft[1] = player2SpriteSheet.crop(168,116,16,30);

            luigiBigWalkRight[0] = player2SpriteSheet.crop(234,116,15,30);
            luigiBigWalkRight[1] = player2SpriteSheet.crop(264,116,16,30);

            luigiBigRunLeft[0] = player2SpriteSheet.crop(199,116,15,30);
            luigiBigRunLeft[1] = player2SpriteSheet.crop(138,116,19,29);

            luigiBigRunRight[0] = player2SpriteSheet.crop(234,116,15,30);
            luigiBigRunRight[1] = player2SpriteSheet.crop(294,116,16,29);

            luigiBigJumpLeft[0] = player2SpriteSheet.crop(198,156,16,29);
            luigiBigJumpLeft[1] = player2SpriteSheet.crop(168,157,16,28);
            luigiBigJumpLeft[2] = player2SpriteSheet.crop(85,156,15,29);//up
            luigiBigJumpLeft[3] = player2SpriteSheet.crop(111,163,16,15);//down
            luigiBigJumpLeft[4] = player2SpriteSheet.crop(54,155,16,31);//change

            luigiBigJumpRight[0] = player2SpriteSheet.crop(234,156,16,29);
            luigiBigJumpRight[1] = player2SpriteSheet.crop(264,157,16,28);
            luigiBigJumpRight[2] = player2SpriteSheet.crop(348,155,15,30);//up
            luigiBigJumpRight[3] = player2SpriteSheet.crop(321,163,16,15);//down
            luigiBigJumpRight[4] = player2SpriteSheet.crop(378,155,16,31);//Change
            
            
            //wario sprites
            //small
            warioSmallWalkLeft[0] = player1SpriteSheet2.crop(203,59,14,20);
            warioSmallWalkLeft[1] = player1SpriteSheet2.crop(177,59,15,19);
            
            warioSmallWalkRight[0] = player1SpriteSheet2.crop(235,59,14,20);
            warioSmallWalkRight[1] = player1SpriteSheet2.crop(260,59,15,19);
            
            warioSmallJumpLeft[0] = player1SpriteSheet2.crop(203,85,16,22);
            warioSmallJumpLeft[1] = player1SpriteSheet2.crop(175,88,16,20);
            warioSmallJumpLeft[2] = player1SpriteSheet2.crop(98,88,14,21);//up
            warioSmallJumpLeft[3] = player1SpriteSheet2.crop(123,92,15,14);//down
            
            warioSmallJumpRight[0] = player1SpriteSheet2.crop(233,85,16,22);
            warioSmallJumpRight[1] = player1SpriteSheet2.crop(261,88,16,20);
            warioSmallJumpRight[2] = player1SpriteSheet2.crop(340,88,14,21);//up
            warioSmallJumpRight[3] = player1SpriteSheet2.crop(314,92,15,14);//down
            
            //big
            warioBigWalkLeft[0] = player1SpriteSheet2.crop(203,146,16,27);
            warioBigWalkLeft[1] = player1SpriteSheet2.crop(173,146,17,27);
            
            warioBigWalkRight[0] = player1SpriteSheet2.crop(233,146,16,27);
            warioBigWalkRight[1] = player1SpriteSheet2.crop(262,146,17,27);
            
            warioBigRunLeft[0] = player1SpriteSheet2.crop(203,146,16,27);
            warioBigRunLeft[1] = player1SpriteSheet2.crop(142,146,18,27);

            warioBigRunRight[0] = player1SpriteSheet2.crop(233,146,16,27);
            warioBigRunRight[1] = player1SpriteSheet2.crop(292,146,18,27);
            
            warioBigJumpLeft[0] = player1SpriteSheet2.crop(201,183,19,29);
            warioBigJumpLeft[1] = player1SpriteSheet2.crop(172,185,19,28);
            warioBigJumpLeft[2] = player1SpriteSheet2.crop(91,185,16,26);//up
            warioBigJumpLeft[3] = player1SpriteSheet2.crop(116,191,18,15);//down
            warioBigJumpLeft[4] = player1SpriteSheet2.crop(64,185,18,27);//change
            
            warioBigJumpRight[0] = player1SpriteSheet2.crop(232,183,19,29);
            warioBigJumpRight[1] = player1SpriteSheet2.crop(261,185,19,28);
            warioBigJumpRight[2] = player1SpriteSheet2.crop(345,185,16,26);//up
            warioBigJumpRight[3] = player1SpriteSheet2.crop(318,191,18,15);//down
            warioBigJumpRight[4] = player1SpriteSheet2.crop(370,185,18,27);//change
            
            //yoshi sprites
            //small
            yoshiSmallWalkLeft[0] = player2SpriteSheet2Small.crop(1001,152,26,30);
            yoshiSmallWalkLeft[1] = player2SpriteSheet2Small.crop(809,153,26,29);
            
            yoshiSmallWalkRight[0] = player2SpriteSheet2Small.crop(100,152,26,30);
            yoshiSmallWalkRight[1] = player2SpriteSheet2Small.crop(292,153,26,29);
            
            yoshiSmallJumpLeft[0] = player2SpriteSheet2Small.crop(1097,51,25,33);
            yoshiSmallJumpLeft[1] = player2SpriteSheet2Small.crop(1038,51,23,27);
            yoshiSmallJumpLeft[2] = player2SpriteSheet2Small.crop(568,8,26,30);//up
            yoshiSmallJumpLeft[3] = player2SpriteSheet2Small.crop(612,22,24,16);//down
            
            yoshiSmallJumpRight[0] = player2SpriteSheet2Small.crop(5,51,25,33);
            yoshiSmallJumpRight[1] = player2SpriteSheet2Small.crop(66,51,23,27);
            yoshiSmallJumpRight[2] = player2SpriteSheet2Small.crop(533,8,26,30);//up
            yoshiSmallJumpRight[3] = player2SpriteSheet2Small.crop(491,22,24,16);//down
            
            yoshiSmallJumpLeftextra[0] = player2SpriteSheet2Small.crop(822,50,30,26);
            yoshiSmallJumpLeftextra[1] = player2SpriteSheet2Small.crop(788,50,27,30);
            yoshiSmallJumpLeftextra[2] = player2SpriteSheet2Small.crop(759,50,22,29);
            yoshiSmallJumpLeftextra[3] = player2SpriteSheet2Small.crop(728,50,25,25);
            yoshiSmallJumpLeftextra[4] = player2SpriteSheet2Small.crop(700,50,22,24);
            yoshiSmallJumpLeftextra[5] = player2SpriteSheet2Small.crop(669,50,24,28);
            yoshiSmallJumpLeftextra[6] = player2SpriteSheet2Small.crop(622,50,26,25);
            yoshiSmallJumpLeftextra[7] = player2SpriteSheet2Small.crop(585,60,26,18);
            
            yoshiSmallJumpRightextra[0] = player2SpriteSheet2Small.crop(275,50,30,26);
            yoshiSmallJumpRightextra[1] = player2SpriteSheet2Small.crop(312,50,27,30);
            yoshiSmallJumpRightextra[2] = player2SpriteSheet2Small.crop(346,50,22,29);
            yoshiSmallJumpRightextra[3] = player2SpriteSheet2Small.crop(374,50,25,25);
            yoshiSmallJumpRightextra[4] = player2SpriteSheet2Small.crop(405,50,22,24);
            yoshiSmallJumpRightextra[5] = player2SpriteSheet2Small.crop(434,50,24,28);
            yoshiSmallJumpRightextra[6] = player2SpriteSheet2Small.crop(479,50,26,25);
            yoshiSmallJumpRightextra[7] = player2SpriteSheet2Small.crop(516,60,26,18);
            
            //big
            yoshiBigWalkLeft[0] = player2SpriteSheet2Big.crop(941,125,26,32);
            yoshiBigWalkLeft[1] = player2SpriteSheet2Big.crop(912,126,25,31);
            
            yoshiBigWalkRight[0] = player2SpriteSheet2Big.crop(39,125,26,32);
            yoshiBigWalkRight[1] = player2SpriteSheet2Big.crop(69,126,25,31);
            
            yoshiBigRunLeft[0] = player2SpriteSheet2Big.crop(941,125,26,32);
            yoshiBigRunLeft[1] = player2SpriteSheet2Big.crop(883,126,26,31);

            yoshiBigRunRight[0] = player2SpriteSheet2Big.crop(39,125,26,32);
            yoshiBigRunRight[1] = player2SpriteSheet2Big.crop(97,126,26,31);
            
            yoshiBigJumpLeft[0] = player2SpriteSheet2Big.crop(854,127,26,30);
            yoshiBigJumpLeft[1] = player2SpriteSheet2Big.crop(830,167,26,26);
            yoshiBigJumpLeft[2] = player2SpriteSheet2Big.crop(943,198,25,32);//up
            yoshiBigJumpLeft[3] = player2SpriteSheet2Big.crop(868,305,30,24);//down
            yoshiBigJumpLeft[4] = player2SpriteSheet2Big.crop(923,298,23,31);//change
            
            yoshiBigJumpRight[0] = player2SpriteSheet2Big.crop(126,127,26,31);
            yoshiBigJumpRight[1] = player2SpriteSheet2Big.crop(150,167,26,26);
            yoshiBigJumpRight[2] = player2SpriteSheet2Big.crop(38,198,25,32);//up
            yoshiBigJumpRight[3] = player2SpriteSheet2Big.crop(108,305,30,24);//down
            yoshiBigJumpRight[4] = player2SpriteSheet2Big.crop(60,298,23,31);//change
            
            item[0] =  ImageIO.read(getClass().getResourceAsStream("/Sheets/item/SL1.png"));
            item[1] =  ImageIO.read(getClass().getResourceAsStream("/Sheets/item/SL2.png"));
            item[2] =  ImageIO.read(getClass().getResourceAsStream("/Sheets/item/SL3.png"));
            item[3] =  ImageIO.read(getClass().getResourceAsStream("/Sheets/item/SL4.png"));
            item[4] =  ImageIO.read(getClass().getResourceAsStream("/Sheets/item/SL5.png"));
            item[5] =  ImageIO.read(getClass().getResourceAsStream("/Sheets/item/SL6.png"));
            item[6] =  ImageIO.read(getClass().getResourceAsStream("/Sheets/item/SL7.png"));
            item[7] =  ImageIO.read(getClass().getResourceAsStream("/Sheets/item/SL8.png"));
            item[8] =  ImageIO.read(getClass().getResourceAsStream("/Sheets/item/SL9.png"));
            item[9] =  ImageIO.read(getClass().getResourceAsStream("/Sheets/item/SL10.png"));
            item[10] =  item[9];
            item[11] =  item[8];
            item[12] =  item[7];
            item[13] =  item[6];
            item[14] =  item[5];
            item[15] =  item[4];
            item[16] =  item[3];
            item[17] =  item[2];
            item[18] =  item[1];
          
            hitWall[0] = ImageIO.read(getClass().getResourceAsStream("/Sheets/KO/KO1.png"));
            hitWall[1] = ImageIO.read(getClass().getResourceAsStream("/Sheets/KO/KO2.png"));
            hitWall[2] = ImageIO.read(getClass().getResourceAsStream("/Sheets/KO/KO3.png"));
            hitWall[3] = ImageIO.read(getClass().getResourceAsStream("/Sheets/KO/KO4.png"));
            hitWall[4] = ImageIO.read(getClass().getResourceAsStream("/Sheets/KO/KO5.png"));
            hitWall[5] = ImageIO.read(getClass().getResourceAsStream("/Sheets/KO/KO6.png"));
            hitWall[6] = ImageIO.read(getClass().getResourceAsStream("/Sheets/KO/KO7.png"));
            hitWall[7] = ImageIO.read(getClass().getResourceAsStream("/Sheets/KO/KO8.png"));
            hitWall[8] = ImageIO.read(getClass().getResourceAsStream("/Sheets/KO/KO9.png"));
            hitWall[9] = ImageIO.read(getClass().getResourceAsStream("/Sheets/KO/KO10.png"));
            hitWall[10] = ImageIO.read(getClass().getResourceAsStream("/Sheets/KO/KO11.png"));
            hitWall[11] = ImageIO.read(getClass().getResourceAsStream("/Sheets/KO/KO12.png"));
            hitWall[12] = ImageIO.read(getClass().getResourceAsStream("/Sheets/KO/KO13.png"));
            hitWall[13] = ImageIO.read(getClass().getResourceAsStream("/Sheets/KO/KO14.png"));
            hitWall[14] = ImageIO.read(getClass().getResourceAsStream("/Sheets/KO/KO15.png"));
            hitWall[15] = ImageIO.read(getClass().getResourceAsStream("/Sheets/KO/KO17.png"));
            hitWall[16] = ImageIO.read(getClass().getResourceAsStream("/Sheets/KO/KO18.png"));
            hitWall[17] = ImageIO.read(getClass().getResourceAsStream("/Sheets/KO/KO19.png"));
            hitWall[18] = ImageIO.read(getClass().getResourceAsStream("/Sheets/KO/KO20.png"));
            hitWall[19] = ImageIO.read(getClass().getResourceAsStream("/Sheets/KO/KO21.png"));
            hitWall[20] = ImageIO.read(getClass().getResourceAsStream("/Sheets/KO/KO22.png"));
            hitWall[21] = ImageIO.read(getClass().getResourceAsStream("/Sheets/KO/KO23.png"));
            hitWall[22] = ImageIO.read(getClass().getResourceAsStream("/Sheets/KO/KO24.png"));
            hitWall[23] = ImageIO.read(getClass().getResourceAsStream("/Sheets/KO/KO25.png"));
            hitWall[24] = ImageIO.read(getClass().getResourceAsStream("/Sheets/KO/KO26.png"));
            hitWall[25] = ImageIO.read(getClass().getResourceAsStream("/Sheets/KO/KO27.png"));
            hitWall[26] = ImageIO.read(getClass().getResourceAsStream("/Sheets/KO/KO28.png"));
            hitWall[27] = ImageIO.read(getClass().getResourceAsStream("/Sheets/KO/KO29.png"));
            hitWall[28] = ImageIO.read(getClass().getResourceAsStream("/Sheets/KO/KO30.png"));
            hitWall[29] = ImageIO.read(getClass().getResourceAsStream("/Sheets/KO/KO31.png"));
            hitWall[30] = ImageIO.read(getClass().getResourceAsStream("/Sheets/KO/KO32.png"));
            hitWall[31] = ImageIO.read(getClass().getResourceAsStream("/Sheets/KO/KO33.png"));
            hitWall[32] = ImageIO.read(getClass().getResourceAsStream("/Sheets/KO/KO35.png"));
            hitWall[33] = ImageIO.read(getClass().getResourceAsStream("/Sheets/KO/KO36.png"));
            hitWall[34] = ImageIO.read(getClass().getResourceAsStream("/Sheets/KO/KO37.png"));
            hitWall[35] = ImageIO.read(getClass().getResourceAsStream("/Sheets/KO/KO38.png"));
            hitWall[36] = ImageIO.read(getClass().getResourceAsStream("/Sheets/KO/KO39.png"));
            hitWall[37] = ImageIO.read(getClass().getResourceAsStream("/Sheets/KO/KO40.png"));
            hitWall[38] = ImageIO.read(getClass().getResourceAsStream("/Sheets/KO/KO41.png"));
            hitWall[39] = ImageIO.read(getClass().getResourceAsStream("/Sheets/KO/KO42.png"));
            hitWall[40] = ImageIO.read(getClass().getResourceAsStream("/Sheets/KO/KO43.png"));
            hitWall[41] = ImageIO.read(getClass().getResourceAsStream("/Sheets/KO/KO44.png"));
            hitWall[42] = ImageIO.read(getClass().getResourceAsStream("/Sheets/KO/KO45.png"));
            hitWall[43] = ImageIO.read(getClass().getResourceAsStream("/Sheets/KO/KO46.png"));

            enemy[0] = SSpriteSheet.crop(8,14,64,45);
            enemy[1] = SSpriteSheet.crop(88,21,60,38);
            enemy[2] = SSpriteSheet.crop(165,25,55,37);
            enemy[3] = SSpriteSheet.crop(242,28,50,35);
            enemy[4] = SSpriteSheet.crop(315,28,48,34);
            enemy[5] = SSpriteSheet.crop(381,27,57,35);
            enemy[6] = SSpriteSheet.crop(454,25,61,35);
            enemy[7] = SSpriteSheet.crop(525,19,62,37);
            enemy[8] = SSpriteSheet.crop(75,89,51,41);

            enemyFG[0] = SAttackSpriteSheet.crop(3,115,66,45);
            enemyFG[1] = SAttackSpriteSheet.crop(72,103,71,55);
            enemyFG[2] =  SAttackSpriteSheet.crop(149,101,64,62);
            enemyFG[3] =  SAttackSpriteSheet.crop(215,102,80,62);
            enemyFG[4] =  SAttackSpriteSheet.crop(5,167,79,62);
            enemyFG[5] =  SAttackSpriteSheet.crop(90,167,93,62);
            enemyFG[6] = SAttackSpriteSheet.crop(188,167,99,72);
            enemyFG[7] =  enemyFG[1];
            enemyFG[8] =  enemyFG[0];

            enemyGB1[0] = SAttackSpriteSheet.crop(3,248,53,48);
            enemyGB1[1] = SAttackSpriteSheet.crop(68,247,61,46);
            enemyGB1[2] =  SAttackSpriteSheet.crop(147,252,48,40);


            enemyGB2[0] = SAttackSpriteSheet.crop(205,250,34,41);
            enemyGB2[1] = SAttackSpriteSheet.crop(252,251,45,44);


            enemyGB3[0] = enemyGB1[2];
            enemyGB3[1] = enemyGB1[1];
            enemyGB3[2] =  enemyGB1[0];

            enemyPS= SAttackSpriteSheet.crop(68,358,29,64);
            enemyHT= SAttackSpriteSheet.crop(178,357,52,62);
            enemySmash= SAttackSpriteSheet.crop(9,302,51,41);
            enemyBL= SAttackSpriteSheet.crop(188,184,41,55);

            //maps
            testMap = ImageIO.read(getClass().getResourceAsStream("/maps/testmap1.png"));
            testMapMultiplayer = ImageIO.read(getClass().getResourceAsStream("/maps/testmap1_multiplayer.png"));
            
            testMaptwo = ImageIO.read(getClass().getResourceAsStream("/maps/testmap2.png"));
            testMaptwoMultiplayer = ImageIO.read(getClass().getResourceAsStream("/maps/testmap2_multiplayer.png"));
            
            helloworldMap =  ImageIO.read(getClass().getResourceAsStream("/maps/Hello World2.png"));
            helloworld_mult_Map = ImageIO.read(getClass().getResourceAsStream("/maps/Hello World(multiplayer)2.png"));
            
            marioAndLuigiRace =  ImageIO.read(getClass().getResourceAsStream("/maps/Mario&Luigi Race.png"));
            marioRaceSolo = ImageIO.read(getClass().getResourceAsStream("/maps/MarioRaceSolo.png"));
            
            

            //blocks
            boundBlock = interactableSpriteSheet.crop(12,73,16,16);
            misteryBlock = interactableSpriteSheet.crop(32,93,16,16);
            surfaceBlock = interactableSpriteSheet.crop(112,93,16,16);
            alternateSurfaceBlock = interactableSpriteSheet.crop(132,133,16,16);
            
            cloud = interactableSpriteSheet.crop(112,132,16,16);
            
            finishBlock = interactableSpriteSheet.crop(72,73,16,16);
            
            rotatingmisteryBlock[0] = interactableSpriteSheet.crop(13, 172, 16, 16);
            rotatingmisteryBlock[1] = interactableSpriteSheet.crop(33, 172, 16, 16);
            rotatingmisteryBlock[2] = interactableSpriteSheet.crop(53, 172, 16, 16);
            rotatingmisteryBlock[3] = interactableSpriteSheet.crop(72, 172, 16, 16);
            
            
            //breakBlock = blockSpriteSheet.crop(272,112,16,16);
            breakBlock = ImageIO.read(getClass().getResourceAsStream("/Sheets/brick.png"));

            //items
            mushroom = interactableSpriteSheet.crop(112,34,16,16);

            coin[0] = interactableSpriteSheet.crop(14,14,12,16);
            coin[1] = interactableSpriteSheet.crop(29,14,12,16);
            coin[2] = interactableSpriteSheet.crop(39,14,12,16);
            coin[3] = interactableSpriteSheet.crop(49,14,12,16);
            //enemy
            goomba[0]=goombaSpriteSheet.crop(119,40,162,162);
            goomba[1]= goombaSpriteSheet.crop(329,40,162,162);
            goombaDies=goombaSpriteSheet.crop(539,100,162,81);
            
            deathBlock = enemySheet.crop(428, 253, 32, 32);
            
            floatingBlock[0] = floatingblockSheet.crop(0, 4, 54, 26);
            floatingBlock[1] = floatingblockSheet.crop(0, 34, 54, 26);

            Lannoyingpest[0] = annoyingpestSheet.crop(2, 9, 33, 25);
            Lannoyingpest[1] = annoyingpestSheet.crop(50, 9, 32, 25);
            Lannoyingpest[2] = annoyingpestSheet.crop(100, 9, 32, 25);
            Lannoyingpest[3] = annoyingpestSheet.crop(147, 9, 32, 25);
            
            Rannoyingpest[0] = annoyingpestSheet.crop(4, 41, 32, 25);
            Rannoyingpest[1] = annoyingpestSheet.crop(50, 41, 32, 25);
            Rannoyingpest[2] = annoyingpestSheet.crop(100,41, 32, 25);
            Rannoyingpest[3] = annoyingpestSheet.crop(147, 41, 33, 25);

        }catch (IOException e) {
        e.printStackTrace();
    }


    }

    public static BufferedImage loadImage(String path) {
        try {
            return ImageIO.read(Images.class.getResourceAsStream(path));
        } catch (IOException e) {
            e.printStackTrace();
            System.exit(1);
        }
        return null;
    }   
    public static  void makeMap(int i, int j, int k, int z,  Map map, Handler h) {
    	for(int x = i; x < k; x++) {
    		map.addBlock(new BreakBlock( x * j, z * j, j, j, h));
    	}	
    }

    public static BufferedImage tint(BufferedImage src, float r, float g, float b) {

        // Copy image ( who made that so complicated :< )
        BufferedImage newImage = new BufferedImage(src.getWidth(), src.getHeight(), BufferedImage.TRANSLUCENT);
        Graphics2D graphics = newImage.createGraphics();
        graphics.drawImage(src, 0, 0, null);
        graphics.dispose();

        // Color image
        for (int i = 0; i < newImage.getWidth(); i++) {
            for (int j = 0; j < newImage.getHeight(); j++) {
                int ax = newImage.getColorModel().getAlpha(newImage.getRaster().getDataElements(i, j, null));
                int rx = newImage.getColorModel().getRed(newImage.getRaster().getDataElements(i, j, null));
                int gx = newImage.getColorModel().getGreen(newImage.getRaster().getDataElements(i, j, null));
                int bx = newImage.getColorModel().getBlue(newImage.getRaster().getDataElements(i, j, null));
                rx *= r;
                gx *= g;
                bx *= b;
                newImage.setRGB(i, j, (ax << 24) | (rx << 16) | (gx << 8) | (bx << 0));
            }
        }
        return newImage;
    }



}
