#include <stdio.h>
#include <stdbool.h>
#include <string.h>
#include <time.h>
#include <stdlib.h>


typedef struct Jogador
{
    int id;
    char nome[50];
    int altura;
    int peso;
    char universidade[100];
    int anoNascimento;
    char cidadeNascimento[50];
    char estadoNascimento[50];
} Jogador;

void split(char linha[], char substrings[8][100])
{
    int qtSubstrings = 0;
    int cS = 0; 
    int c = 0;  
    for (int i = 0; i < 8; i++)
    {
        for (int j = 0; j < 100; j++)
        {
            substrings[i][j] = '\0';
        }
    }
    while (linha[c] != '\0')
    {
        if (linha[c] != ',')
        {
            while (linha[c] != ',' && linha[c] != '\0')
            {
                if (linha[c] == '\n')
                    c++; 
                else
                {
                    substrings[qtSubstrings][cS] = linha[c];
                    c++;
                    cS++;
                }
            }
            cS = 0;
            qtSubstrings++;
        }
        else
        {
            if (linha[c + 1] == ',' || linha[c + 1] == '\n' || linha[c + 1] == '\0')
            {
                strcpy(substrings[qtSubstrings], "nao informado");
                qtSubstrings++;
            }
            c++;
        }
    }
}


void leitura(Jogador jogadores[], FILE *file)
{

    char linha[200];
    int qtJogadores = -1; 

    while (fgets(linha, sizeof(linha), file) != NULL)
    {
        char substrings[8][100];
        if (qtJogadores >= 0)
        {
            split(linha, substrings);
            int ID = atoi(substrings[0]);
            int h = atoi(substrings[2]);
            int p = atoi(substrings[3]);
            int ano = atoi(substrings[5]);

            jogadores[qtJogadores].id = ID;
            strcpy(jogadores[qtJogadores].nome, substrings[1]);
            jogadores[qtJogadores].altura = h;
            jogadores[qtJogadores].peso = p;
            strcpy(jogadores[qtJogadores].universidade, substrings[4]);
            jogadores[qtJogadores].anoNascimento = ano;
            strcpy(jogadores[qtJogadores].cidadeNascimento, substrings[6]);
            strcpy(jogadores[qtJogadores].estadoNascimento, substrings[7]);
            qtJogadores++;
        }
        else
            qtJogadores++;
    }
}

void print(int identificador, Jogador jogadores[])
{
    printf("[%d ## %s ## %d ## %d ## %d ## %s ## %s ## %s]\n",
           jogadores[identificador].id,
           jogadores[identificador].nome,
           jogadores[identificador].altura,
           jogadores[identificador].peso,
           jogadores[identificador].anoNascimento,
           jogadores[identificador].universidade,
           jogadores[identificador].cidadeNascimento,
           jogadores[identificador].estadoNascimento);
}

void Sort(int size, Jogador jogador[], int place) {


    int max = (jogador[0].id / place) % 10;
    Jogador output[size];

        for (int i = 0; i < size; i++) {
            if((jogador[i].id / place) % 10 > max){
                max = (jogador[i].id / place) % 10;
                max = jogador[i].id;
            }
            
        }

         int count[max+1];
        
        for(int i = 0; i < max+1;i ++){
            count[i] = 0;
        }
        for(int i = 0; i < size; i++){
            count[(jogador[i].id / place) % 10]++;
        }
        for(int i = 1; i < max+1; i++){
            count[i] += count[i-1];
        }

        for(int i = size-1; i >= 0; i--){
            output[count[(jogador[i].id / place) % 10] - 1] = jogador[i];
            count[(jogador[i].id / place) % 10]--;
        }

        for(int i = 0; i < size; i++){
            jogador[i] = output[i];
        }
    }

int getMaximo(Jogador Jogador[], int tam){
    int max = Jogador[0].id;
    for(int i = 1; i < tam; i++){
        if(Jogador[i].id > max){
            max = Jogador[i].id;
        }
    }
    return max;
}


void RadixSort(Jogador jogadores[], int tam){

    int place;
    int max= getMaximo(jogadores, tam);
    
    for(place = 1; max/place > 0; place*=10)
    {
       Sort(tam, jogadores, place);
       
    }
    


}

int main()
{

    char id[500];
    Jogador players[3922];
    Jogador subPlayers[500];
    int a = 0;
    FILE *file = fopen("/tmp/players.csv", "r");
    do
    {
        scanf("%s", id);
        if (strcmp(id, "FIM") != 0 && strcmp(id, "fim") != 0)
        {
            int identificador = atoi(id);
            leitura(players, file);
            subPlayers[a] = players[identificador];
            a++;
        }
    } while ((strcmp(id, "FIM") != 0) && (strcmp(id, "fim") != 0));
    fclose(file);

    RadixSort(subPlayers, a);

    for (int i = 0; i < a; i++)
    {
        print(i, subPlayers);
    }

}