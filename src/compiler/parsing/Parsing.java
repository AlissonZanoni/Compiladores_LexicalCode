package compiler.parsing;

public class Parsing {
    int tabParsing[][] = new int[83][51];

    Parsing(){
        //inicializar a Matriz de Parsing com zeros.
        for(int i=0; i<83; i++){
            for(int j=0; j<51; j++){
                tabParsing[i][j] = 0;
            }
        }

        //inicializar os outros elementos da Matriz de Parsing.
        tabParsing[51][2] = 1;
        tabParsing[52][2] = 3;
        tabParsing[52][3] = 3;
        tabParsing[52][7] = 2;
        tabParsing[52][13] = 3;
        tabParsing[52][15] = 3;
        tabParsing[52][19] = 3;
        tabParsing[52][26] = 3;
        tabParsing[52][47] = 3;
        tabParsing[53][2] = 13;
        tabParsing[53][3] = 13;
        tabParsing[53][13] = 13;
        tabParsing[53][15] = 19;
        tabParsing[53][19] = 13;
        tabParsing[53][26] = 13;
        tabParsing[54][15] = 31;
        tabParsing[55][41] = 4;
        tabParsing[55][43] = 5;
        tabParsing[56][3] = 8;
        tabParsing[56][13] = 6;
        tabParsing[56][19] = 7;
        tabParsing[56][26] = 9;
        tabParsing[57][2] = 10;
        tabParsing[57][3] = 10;
        tabParsing[57][7] = 11;
        tabParsing[57][13] = 10;
        tabParsing[57][15] = 10;
        tabParsing[57][19] = 10;
        tabParsing[57][26] = 10;
        tabParsing[57][47] = 10;
        tabParsing[59][7] = 12;
        tabParsing[60][2] = 15;
        tabParsing[60][3] = 18;
        tabParsing[60][13] = 14;
        tabParsing[60][19] = 17;
        tabParsing[60][26] = 16;
        tabParsing[61][39] = 26;
        tabParsing[61][46] = 27;
        tabParsing[62][5] = 20;
        tabParsing[62][6] = 21;
        tabParsing[62][7] = 22;
        tabParsing[62][8] = 23;
        tabParsing[62][10] = 24;
        tabParsing[62][45] = 25;
        tabParsing[63][3] = 28;
        tabParsing[63][13] = 28;
        tabParsing[63][19] = 28;
        tabParsing[63][26] = 28;
        tabParsing[64][40] = 29;
        tabParsing[64][45] = 30;
        tabParsing[65][1] = 52;
        tabParsing[65][7] = 34;
        tabParsing[65][8] = 36;
        tabParsing[65][10] = 35;
        tabParsing[65][16] = 49;
        tabParsing[65][18] = 64;
        tabParsing[65][23] = 67;
        tabParsing[65][24] = 69;
        tabParsing[65][25] = 68;
        tabParsing[65][27] = 39;
        tabParsing[65][40] = 38;
        tabParsing[66][1] = 33;
        tabParsing[66][7] = 33;
        tabParsing[66][8] = 33;
        tabParsing[66][10] = 33;
        tabParsing[66][16] = 33;
        tabParsing[66][18] = 33;
        tabParsing[66][20] = 32;
        tabParsing[66][23] = 33;
        tabParsing[66][24] = 33;
        tabParsing[66][25] = 33;
        tabParsing[66][27] = 33;
        tabParsing[66][38] = 32;
        tabParsing[67][5] = 75;
        tabParsing[67][6] = 75;
        tabParsing[67][7] = 75;
        tabParsing[67][8] = 75;
        tabParsing[67][10] = 75;
        tabParsing[67][27] = 76;
        tabParsing[67][46] = 75;
        tabParsing[68][40] = 40;
        tabParsing[68][45] = 40;
        tabParsing[68][46] = 41;
        tabParsing[69][5] = 44;
        tabParsing[69][6] = 46;
        tabParsing[69][7] = 48;
        tabParsing[69][8] = 47;
        tabParsing[69][10] = 45;
        tabParsing[70][40] = 42;
        tabParsing[70][43] = 43;
        tabParsing[70][45] = 42;
        tabParsing[71][29] = 56;
        tabParsing[71][30] = 55;
        tabParsing[71][31] = 53;
        tabParsing[71][33] = 58;
        tabParsing[71][35] = 57;
        tabParsing[71][48] = 54;
        tabParsing[72][21] = 50;
        tabParsing[72][40] = 51;
        tabParsing[72][43] = 51;
        tabParsing[72][45] = 51;
        tabParsing[73][5] = 59;
        tabParsing[73][6] = 60;
        tabParsing[73][7] = 63;
        tabParsing[73][8] = 62;
        tabParsing[73][10] = 61;
        tabParsing[74][36] = 65;
        tabParsing[74][49] = 66;
        tabParsing[75][34] = 71;
        tabParsing[75][40] = 70;
        tabParsing[75][45] = 70;
        tabParsing[76][34] = 73;
        tabParsing[76][40] = 73;
        tabParsing[76][43] = 74;
        tabParsing[76][45] = 73;
        tabParsing[79][5] = 80;
        tabParsing[79][6] = 80;
        tabParsing[79][7] = 80;
        tabParsing[79][8] = 80;
        tabParsing[79][10] = 80;
        tabParsing[79][46] = 80;
        tabParsing[80][37] = 77;
        tabParsing[80][40] = 79;
        tabParsing[80][43] = 79;
        tabParsing[80][45] = 79;
        tabParsing[80][46] = 79;
        tabParsing[80][50] = 78;
        tabParsing[81][5] = 84;
        tabParsing[81][6] = 85;
        tabParsing[81][7] = 86;
        tabParsing[81][8] = 88;
        tabParsing[81][10] = 87;
        tabParsing[81][46] = 89;
        tabParsing[82][37] = 81;
        tabParsing[82][40] = 81;
        tabParsing[82][42] = 83;
        tabParsing[82][43] = 81;
        tabParsing[82][44] = 82;
        tabParsing[82][45] = 81;
        tabParsing[82][46] = 81;
        tabParsing[82][50] = 81;

//        //BLOCO
//        tabParsing[51][2] = 1;//void
//
//        //DCLVAR
//        tabParsing[52][2] = 3;
//        tabParsing[52][3] = 3;
//        tabParsing[52][7] = 2;//nomevariavel
//        tabParsing[52][13] = 3;
//        tabParsing[52][15] = 3;
//        tabParsing[52][19] = 3;
//        tabParsing[52][26] = 3;
//        tabParsing[52][47] = 3;
//
//        //DCLFUNC
//        tabParsing[53][2] = 13;//void
//        tabParsing[53][3] = 13;//string
//        tabParsing[53][13] = 13;//integer
//        tabParsing[53][15] = 19;
//        tabParsing[53][19] = 13;//float
//        tabParsing[53][26] = 13;//char
//
//        //CORPO
//        tabParsing[54][15] = 31;
////
//        //REPIDENT
//        tabParsing[55][41] = 4;
//        tabParsing[55][43] = 5;//,
//
//        //TIPO
//        tabParsing[56][3] = 8;//string
//        tabParsing[56][13] = 6;//integer
//        tabParsing[56][19] = 7;//float
//        tabParsing[56][26] = 9;//char
//
//        //LDVAR
//        tabParsing[57][2] = 10;
//        tabParsing[57][3] = 10;
//        tabParsing[57][7] = 11;//nomevariavel
//        tabParsing[57][13] = 10;
//        tabParsing[57][15] = 10;
//        tabParsing[57][19] = 10;
//        tabParsing[57][26] = 10;
//        tabParsing[57][47] = 10;
//
//        //LID
//        tabParsing[59][7] = 12;//nomevariavel
//
//        //TIPO_RETORNO
//        tabParsing[60][2] = 15;//void
//        tabParsing[60][3] = 18;//string
//        tabParsing[60][13] = 14;//integer
//        tabParsing[60][19] = 17;//float
//        tabParsing[60][26] = 16;//char
//
//        //DEFPAR
//        tabParsing[61][39] = 26;
//        tabParsing[61][46] = 27;//(
//
//        //VALORRETORNO
//        tabParsing[62][5] = 20;//numerointeiro
//        tabParsing[62][6] = 21;//numerofloat
//        tabParsing[62][7] = 22;//nomevariavel
//        tabParsing[62][8] = 23;//nomedochar
//        tabParsing[62][10] = 24;//nomedastring
//        tabParsing[62][45] = 25;
//
//        //PARAM
//        tabParsing[63][3] = 28;//string
//        tabParsing[63][13] = 28;//integer
//        tabParsing[63][19] = 28;//float
//        tabParsing[63][26] = 28;//char
//
//        //LPARAM
//        tabParsing[64][40] = 29;
//        tabParsing[64][45] = 30;
//
//        //COMANDO
//        tabParsing[65][1] = 52;//while
//        tabParsing[65][7] = 34;//nomevariavel
//        tabParsing[65][8] = 36;//nomedochar
//        tabParsing[65][10] = 35;//nomedastring
//        tabParsing[65][16] = 49;//if
//        tabParsing[65][18] = 64;//for
//        tabParsing[65][23] = 67;//do
//        tabParsing[65][24] = 69;//cout
//        tabParsing[65][25] = 68;//cin
//        tabParsing[65][27] = 39;//callfuncao
//        tabParsing[65][40] = 38;
//
//        //REPCOMANDO
//        tabParsing[65][1] = 33;//while
//        tabParsing[65][7] = 33;//nomevariavel
//        tabParsing[65][8] = 33;//nomedochar
//        tabParsing[65][10] = 33;//nomedastring
//        tabParsing[65][16] = 33;//if
//        tabParsing[65][18] = 33;//for
//        tabParsing[65][20] = 32;
//        tabParsing[65][23] = 33;//do
//        tabParsing[65][24] = 33;//cout
//        tabParsing[65][25] = 33;//cin
//        tabParsing[65][27] = 33;//callfuncao
//
//
//        tabParsing[66][38] = 32;
//
//        //EXPRESSAO
//        tabParsing[67][5] = 75;//numerointeiro
//        tabParsing[67][6] = 75;//numerofloat
//        tabParsing[67][7] = 75;//nomevariavel
//        tabParsing[67][8] = 75;//nomedochar
//        tabParsing[67][10] = 75;//nomedastring
//        tabParsing[67][27] = 76;//callfuncao
//        tabParsing[67][46] = 75;
//
//        //PARAMETROS
//        tabParsing[68][40] = 40;
//        tabParsing[68][45] = 40;
//        tabParsing[68][46] = 41;//(
//
//        //TPARAM
//        tabParsing[69][5] = 44;//numerointeiro
//        tabParsing[69][6] = 46;//numerofloat
//        tabParsing[69][7] = 48;//nomevariavel
//        tabParsing[69][8] = 47;//nomedochar
//        tabParsing[69][10] = 45;//nomedastring
//
//        //REPPAR
//        tabParsing[70][40] = 42;
//        tabParsing[70][43] = 43;//,
//        tabParsing[70][45] = 42;
//
//        //COMPARACAO
//        tabParsing[71][29] = 56;//>=
//        tabParsing[71][30] = 55;//>
//        tabParsing[71][31] = 53;//==
//        tabParsing[71][33] = 58;//<=
//        tabParsing[71][35] = 57;//<
//        tabParsing[71][48] = 54;//!=
//
//        //ELSEPARTE
//        tabParsing[72][21] = 50;//else
//        tabParsing[72][40] = 51;
//        tabParsing[72][43] = 51;
//        tabParsing[72][45] = 51;
//
//        //CONTCOMPARACAO
//        tabParsing[73][5] = 59;//numerointeiro
//        tabParsing[73][6] = 60;//numerofloat
//        tabParsing[73][7] = 63;//nomevariavel
//        tabParsing[73][8] = 62;//nomedochar
//        tabParsing[73][10] = 61;//nomedastring
//
//        //INCREMENTO
//        tabParsing[74][36] = 65;//++
//        tabParsing[74][49] = 66;//--
//
//        //SEQCOUT
//        tabParsing[75][34] = 71;//<<
//        tabParsing[75][40] = 70;
//        tabParsing[75][45] = 70;
//
//        //SEQUENCIA
//        tabParsing[76][34] = 73;
//        tabParsing[76][40] = 73;
//        tabParsing[76][43] = 74;//,
//        tabParsing[76][45] = 73;
//
//        //TERMO
//        tabParsing[79][5] = 80;//numerointeiro
//        tabParsing[79][6] = 80;//numerofloat
//        tabParsing[79][7] = 80;//nomevariavel
//        tabParsing[79][8] = 80;//nomedochar
//        tabParsing[79][10] = 80;//nomedastring
//        tabParsing[79][46] = 80;//(
//
//        //REPEXP
//        tabParsing[80][37] = 77;//+
//        tabParsing[80][40] = 79;
//        tabParsing[80][43] = 79;
//        tabParsing[80][45] = 79;
//        tabParsing[80][46] = 79;
//        tabParsing[80][50] = 78;//-
//
//        //FATOR
//        tabParsing[81][5] = 84;//numerointeiro
//        tabParsing[81][6] = 85;//numerofloat
//        tabParsing[81][7] = 86;//nomevariavel
//        tabParsing[81][8] = 88;//nomedochar
//        tabParsing[81][10] = 87;//nomedastring
//        tabParsing[81][46] = 89;//(
//
//        //REPTERMO
//        tabParsing[82][37] = 81;
//        tabParsing[82][40] = 81;
//        tabParsing[82][42] = 83;// /
//        tabParsing[82][43] = 81;
//        tabParsing[82][44] = 82;//*
//        tabParsing[82][45] = 81;
//        tabParsing[82][46] = 81;
//        tabParsing[82][50] = 81;
    }

    int producoes[][] = {
            {2,11,39,52,53,54,38,0,0,0,0,0,0,0,0,0},//P1
            {7,55,41,56,40,57,0,0,0,0,0,0,0,0,0,0},//P2
            {17,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},//P3
            {17,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},//P4
            {43,7,55,0,0,0,0,0,0,0,0,0,0,0,0,0},//P5
            {13,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},//P6
            {19,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},//P7
            {3,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},//P8
            {26,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},//P9
            {17,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},//P10
            {59,41,56,40,57,0,0,0,0,0,0,0,0,0,0,0},//P11
            {7,55,0,0,0,0,0,0,0,0,0,0,0,0,0,0},//P12
            {60,7,61,39,52,53,54,4,46,62,45,38,53,0,0,0},//P13
            {13,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},//P14
            {2,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},//P15
            {26,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},//P16
            {19,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},//P17
            {3,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},//P18
            {17,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},//P19
            {5,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},//P20
            {6,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},//P21
            {7,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},//P22
            {8,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},//P23
            {10,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},//P24
            {17,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},//P25
            {17,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},//P26
            {46,63,45,0,0,0,0,0,0,0,0,0,0,0,0,0},//P27
            {56,64,0,0,0,0,0,0,0,0,0,0,0,0,0,0},//P28
            {40,56,64,0,0,0,0,0,0,0,0,0,0,0,0,0},//P29
            {17,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},//P30
            {15,65,40,66,20,0,0,0,0,0,0,0,0,0,0,0},//P31
            {17,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},//P32
            {65,40,66,0,0,0,0,0,0,0,0,0,0,0,0,0},//P33
            {7,32,67,0,0,0,0,0,0,0,0,0,0,0,0,0},//P34
            {10,32,67,0,0,0,0,0,0,0,0,0,0,0,0,0},//P35
            {8,32,67,0,0,0,0,0,0,0,0,0,0,0,0,0},//P36
            {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},//P37
            {17,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},//P38
            {27,7,68,0,0,0,0,0,0,0,0,0,0,0,0,0},//P39
            {17,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},//P40
            {46,69,70,45,0,0,0,0,0,0,0,0,0,0,0,0},//P41
            {17,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},//P42
            {43,69,70,0,0,0,0,0,0,0,0,0,0,0,0,0},//P43
            {5,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},//P44
            {10,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},//P45
            {6,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},//P46
            {8,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},//P47
            {7,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},//P48
            {16,46,7,71,45,39,65,40,66,38,72,0,0,0,0,0},//P49
            {21,39,65,40,66,38,0,0,0,0,0,0,0,0,0,0},//P50
            {17,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},//P51
            {1,46,7,71,45,39,65,40,66,38,0,0,0,0,0,0},//P52
            {31,73,0,0,0,0,0,0,0,0,0,0,0,0,0,0},//P53
            {48,73,0,0,0,0,0,0,0,0,0,0,0,0,0,0},//P54
            {30,73,0,0,0,0,0,0,0,0,0,0,0,0,0,0},//P55
            {29,73,0,0,0,0,0,0,0,0,0,0,0,0,0,0},//P56
            {35,73,0,0,0,0,0,0,0,0,0,0,0,0,0,0},//P57
            {33,73,0,0,0,0,0,0,0,0,0,0,0,0,0,0},//P58
            {5,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},//P59
            {6,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},//P60
            {10,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},//P61
            {8,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},//P62
            {7,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},//P63
            {18,46,7,32,73,40,7,71,40,74,45,39,65,40,66,38},//P64
            {36,5,0,0,0,0,0,0,0,0,0,0,0,0,0,0},//P65
            {49,5,0,0,0,0,0,0,0,0,0,0,0,0,0,0},//P66
            {23,39,65,40,66,38,1,46,7,71,45,0,0,0,0,0},//P67
            {25,28,7,0,0,0,0,0,0,0,0,0,0,0,0,0},//P68
            {24,34,12,75,0,0,0,0,0,0,0,0,0,0,0,0},//P69
            {17,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},//P70
            {34,7,76,75,0,0,0,0,0,0,0,0,0,0,0,0},//P71
            {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},//72
            {17,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},//P73
            {43,7,76,0,0,0,0,0,0,0,0,0,0,0,0,0},//P74
            {79,80,0,0,0,0,0,0,0,0,0,0,0,0,0,0},//P75
            {27,7,68,0,0,0,0,0,0,0,0,0,0,0,0,0},//P76
            {37,79,80,0,0,0,0,0,0,0,0,0,0,0,0,0},//P77
            {50,79,80,0,0,0,0,0,0,0,0,0,0,0,0,0},//P78
            {17,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},//P79
            {81,82,0,0,0,0,0,0,0,0,0,0,0,0,0,0},//P80
            {17,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},//P81
            {44,81,82,0,0,0,0,0,0,0,0,0,0,0,0,0},//P82
            {42,81,82,0,0,0,0,0,0,0,0,0,0,0,0,0},//P83
            {5,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},//P84
            {6,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},//P85
            {7,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},//P86
            {10,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},//P87
            {8,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},//P88
            {46,67,45,0,0,0,0,0,0,0,0,0,0,0,0,0},//P89
    };
}
