#include <stdio.h>
#include <stdbool.h>
#include <string.h>
#include <time.h>
#include <stdlib.h>


typedef struct Jogadores
{
    int id;
    char nome[50];
    int altura;
    int peso;
    char universidade[100];
    int anoNascimento;
    char cidadeNascimento[50];
    char estadoNascimento[50];
} Jogadores;

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

void leitura(Jogadores jogadores[], FILE *file)
{

    char linha[200];
    int qtJogadores = -1; 

    while (fgets(linha, sizeof(linha), file) != NULL)
    {
        char substrings[8][100];
        if (qtJogadores >= 0)
        {
            split(linha, substrings);
            // conversao de strings para inteiros
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

void print(int identificador, Jogadores jogadores[])
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

void Heap(Jogadores players[], int i, int fim) {

        int raiz = i; 
        int esquerda = 2 * i + 1; 
        int direita = 2 * i + 2; 

        if (esquerda < fim && players[esquerda].altura > players[raiz].altura) { 
            
            raiz = esquerda;
        }

        if (direita < fim && players[direita].altura > players[raiz].altura) { 
                                                                                             
            
            raiz = direita;
        }

        if (raiz != i) {
            
            Jogadores aux = players[i];
            players[i] = players[raiz];
            players[raiz] = aux;
           
            Heap(players, raiz, fim); 
                                          
        }

    }

void heapSort(int fim, Jogadores players[]) {

         
       

        for (int i = ((fim / 2) - 1); i >=0; i--) {
            
            Heap(players, i, fim);
        }

      
        for (int i = fim - 1; i >= 0; i--) {
            
            Jogadores aux = players[0];
            players[0] = players[i];
            players[i] = aux;
      
            Heap(players, 0, i);
        }

        for (int i = 0; i < fim; i++) { 
            
            for (int j = i+1; j < fim; j++) {
                
                if (players[i].altura == players[j].altura) {
                    
                    if ( strcmp(players[i].nome, players[j].nome) > 0) {
                        
                        Jogadores aux = players[j];
                        players[j] = players[i];
                        players[i] = aux;
                  
                    }
                }
            }
        }

    

    }


int main()
{

    char id[500];
    Jogadores players[3922];
    Jogadores subPlayers[500];
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

    
    heapSort(a,subPlayers);

     int k= 10;
    Jogadores min[k];
    for (int i = 0; i < k; i++)
    {
        min[i] = subPlayers[i];
    }
    

     for (int i = 0; i < k; i++)
    {
        print(i, min);
    }

 
}