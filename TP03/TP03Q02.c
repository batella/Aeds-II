#include <stdio.h>
#include <stdbool.h>
#include <string.h>
#include <time.h>
#include <stdlib.h>
#define MAXTAM 500
#define MAX_ATTRIBUTES 8
#define MAX_LEN 100 

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


typedef struct Split {
	char linha[MAX_ATTRIBUTES][MAX_LEN];
} Split;

void read(Jogador jogadores[], FILE *file)
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

void print(Jogador jogadores)
{
    printf(" ## %s ## %d ## %d ## %d ## %s ## %s ## %s ##\n",
           jogadores.nome,
           jogadores.altura,
           jogadores.peso,
           jogadores.anoNascimento,
           jogadores.universidade,
           jogadores.cidadeNascimento,
           jogadores.estadoNascimento);
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

Split SplitSpace(){

    Split Split;

    for(int i=0; i<3; i++){
        scanf("%[^ \n]", Split.linha[i]);   
        if(getchar() == '\n') i=3;  
    }

    return Split;

}


typedef struct Lista
{
    Jogador *array;
    int size;

    void (*InserirInicio)(struct Lista *, Jogador x);
    void (*InserirFim)(struct Lista *, Jogador x);
    void (*Inserir)(struct Lista *, Jogador x, int pos);

    Jogador (*RemoverInicio)(struct Lista *);
    Jogador (*RemoverFim)(struct Lista *);
    Jogador (*Remover)(struct Lista *, int pos);

    void (*Mostrar)(struct Lista *);
    void (*MostrarR)(struct Lista *);
    void (*Close)(struct Lista *);

} Lista;

void InserirInicio(Lista *lista, Jogador x)
{

    if (lista->size >= MAXTAM)
    {
        printf("Erro ao inserir no inicio!");
        exit(1);
    }

    for (int i = lista->size; i > 0; i--)
    {
        lista->array[i] = lista->array[i - 1];
    }

    lista->array[0] = x;
    lista->size++;
}

void InserirFim(Lista *lista, Jogador x)
{

    if (lista->size >= MAXTAM)
    {
        printf("Erro ao inserir!");
        exit(1);
    }

    lista->array[lista->size] = x;
    lista->size++;
}

void Inserir(Lista *lista, Jogador x, int pos)
{

    if (lista->size >= MAXTAM || pos < 0 || pos > lista->size)
    {
        printf("Erro ao inserir!");
        exit(1);
    }

    for (int i = lista->size; i > pos; i--)
    {
        lista->array[i] = lista->array[i - 1];
    }

    lista->array[pos] = x;
    lista->size++;
}

Jogador RemoverInicio(Lista *lista)
{
    if (lista->size == 0)
    {
        printf("Erro ao remover!");
        exit(1);
    }

    Jogador removido = lista->array[0];

    for (int i = 0; i < lista->size; i++)
    {
        lista->array[i] = lista->array[i + 1];
    }

    lista->size--;
    return removido;
}

Jogador RemoverFim(Lista *lista)
{

    if (lista->size == 0)
    {
        printf("Erro ao remover!");
        exit(1);
    }

    return lista->array[--lista->size];
}

Jogador Remover(Lista *lista, int pos)
{
    if (lista->size == 0 || pos < 0 || pos >= lista->size)
    {
        printf("Erro ao remover!");
        exit(1);
    }

    Jogador removido = lista->array[pos];

    for (int i = pos; i < lista->size; i++)
    {
        lista->array[i] = lista->array[i + 1];
    }

    lista->size--;
    return removido;
}

void Mostrar(Lista *lista)
{

    for (int i = 0; i < lista->size; i++)
    {
        printf("[%i]", i);
        print(lista->array[i]);
    }
}

void MostrarRec(Lista *lista)
{

    for (int i = 0; i < lista->size; i++)
    {
        printf("(R) %s\n", lista->array[i].nome);
        
    }
}

void CloseLista(Lista *lista)
{
    free(lista->array);
}

Lista newLista()
{

    Lista lista;

    lista.size = 0;
    lista.array = (Jogador *)malloc(MAXTAM * sizeof(Jogador));

    lista.InserirInicio = InserirInicio;
    lista.InserirFim= InserirFim;
    lista.Inserir = Inserir;

    lista.RemoverInicio = RemoverInicio;
    lista.RemoverFim = RemoverFim;
    lista.Remover = Remover;

    lista.MostrarR = MostrarRec;
    lista.Mostrar = Mostrar;
    lista.Close = CloseLista;

    return lista;
}

void doComando(Lista *lista, Jogador jogadores[], Lista* removidos)
{
    
    
    Split split = SplitSpace();
    
    
    if (strcmp(split.linha[0], "II") == 0)
    {
        int valor = atoi(split.linha[1]);

        lista->InserirInicio(lista, jogadores[valor]);
    }

    if (strcmp(split.linha[0], "IF") == 0)
    {
        int valor = atoi(split.linha[1]);

        lista->InserirFim(lista, jogadores[valor]);
    }

    if (strcmp(split.linha[0], "I*") == 0)
    {
        int valor = atoi(split.linha[2]);
        int id = atoi(split.linha[1]);
        lista->Inserir(lista, jogadores[valor], id);
    }

    if (strcmp(split.linha[0], "RI") == 0)
    {
        removidos->InserirFim(removidos, lista->RemoverInicio(lista));
    }

    if (strcmp(split.linha[0], "RF") == 0)
    {
        removidos->InserirFim(removidos, lista->RemoverFim(lista));
    }

    if (strcmp(split.linha[0], "R*") == 0)
    {
        int id = atoi(split.linha[1]);
        removidos->InserirFim(removidos, lista->Remover(lista,id));
    }
}

int main()
{

    char id[50];
    Jogador jogadores[3922];
    Lista lista = newLista();

    FILE *file = fopen("/tmp/players.csv", "r");
    do
    {
        scanf("%s", id);
        if (strcmp(id, "FIM") != 0 && strcmp(id, "fim") != 0)
        {
            int identificador = atoi(id);
            read(jogadores, file);
            lista.InserirFim(&lista, jogadores[identificador]);
        }
    } while ((strcmp(id, "FIM") != 0) && (strcmp(id, "fim") != 0));

    fclose(file);

    
   
    
    Lista removidos = newLista();
    
    int action; scanf("%i", &action); 

    for (int i = 0; i <=action; i++)
    {
        doComando(&lista, jogadores, &removidos);
    }
    

    removidos.MostrarR(&removidos);
    lista.Mostrar(&lista);
    lista.Close(&lista);
    removidos.Close(&removidos);
}