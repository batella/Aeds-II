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

bool pesqBinaria(Jogador jogadores[], char chave[50], int tam)
{
    bool resp = false;
    int dir = (tam - 1), esq = 0, meio;

    while (esq <= dir)
    {
        meio = (esq + dir) / 2;
        int resul = strcmp(chave, jogadores[meio].nome);
        if (resul == 0)
        {
            resp = true;
            esq = dir + 1;
        }
        else
        {
            int c = 0;
            while (chave[c] == jogadores[meio].nome[c])
            {
                c++;
            }
            if (chave[c] > jogadores[meio].nome[c])
            {
                esq = meio + 1;
            }
            else
                dir = meio - 1;
        }
    }
    return resp;
}

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

void Sort(Jogador jogadores[], int tam)
{
    for (int i = 0; i < tam - 1; i++)
    {
        int menor = i;
        for (int j = i + 1; j < tam; j++)
        {
            if (jogadores[j].nome[0] < jogadores[menor].nome[0])
                menor = j;
            else
            {
                if (jogadores[j].nome[0] == jogadores[menor].nome[0])
                {
                    int posChar = 1;
                    while (jogadores[j].nome[posChar] == jogadores[menor].nome[posChar])
                    {
                        posChar++;
                    }
    
                    if (jogadores[j].nome[posChar] < jogadores[menor].nome[posChar])
                        menor = j;
                }
                   }
        }
        
        Jogador aux = jogadores[i];
        jogadores[i] = jogadores[menor];
        jogadores[menor] = aux;
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

int main()
{
    clock_t inicio, fim;
    
    char id[500];
    
    Jogador jogadores[3922];
    Jogador subJogadores[500];
    int numeroJogador = 0;
    FILE *file = fopen("/tmp/players.csv", "r");
    do
    {
        scanf("%s", id);
       
        if (strcmp(id, "FIM") != 0 && strcmp(id, "fim") != 0    )
        {
            int identificador = atoi(id);
            leitura(jogadores, file);
            subJogadores[numeroJogador] = jogadores[identificador];
            numeroJogador++;
        }
    } while (strcmp(id, "FIM") != 0 && strcmp(id, "fim") != 0   );
    fclose(file);
    Sort(subJogadores, numeroJogador);
    char nome[500];
    do
    {
        scanf(" %[^\r\n]%*c", nome);
       
        if (strcmp(nome, "FIM") != 0 && strcmp(nome, "fim") != 0    )
        {
            if (pesqBinaria(subJogadores, nome, numeroJogador))
                printf("SIM\n");
            else
                printf("NAO\n");
        }
    } while (strcmp(nome, "FIM") != 0 && strcmp(nome, "fim") != 0  );
    fim = clock();
    }