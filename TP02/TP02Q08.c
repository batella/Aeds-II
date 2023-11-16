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

void ShellSort(Jogador jogadores[], int tam, int pes, int intervalo)
{
    for (int i = (intervalo + pes); i < tam; i += intervalo)
    {
        Jogador tmp = jogadores[i];
        int j = i - intervalo;
        while ((j >= 0) && (jogadores[j].peso > tmp.peso))
        {
            jogadores[j + intervalo] = jogadores[j];
            j -= intervalo;
        }

        while ((j >= 0) && (strcmp(jogadores[j].nome, tmp.nome) > 0) && (jogadores[j].peso == tmp.peso))
        {
            jogadores[j + intervalo] = jogadores[j];
            j -= intervalo;
        }

        jogadores[j + intervalo] = tmp;
    }
}


void shell(Jogador jogadores[], int tam)
{
    int intervalo = 1; 

    do
    {
        intervalo = (intervalo * 3) + 1;
    } while (intervalo < tam); 

    do
    {
        intervalo /= 3; 
        for (int pes = 0; pes < intervalo; pes++)
        {
           ShellSort(jogadores, tam, pes, intervalo); 
        }
    } while (intervalo != 1);
}

int main()
{

    char id[50];
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

    shell(subPlayers, a);

    for (int i = 0; i < a; i++)
    {
        print(i, subPlayers);
    }

   
}