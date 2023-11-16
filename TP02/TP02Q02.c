#include <stdio.h>
#include <stdlib.h>
#include <string.h>

// Struct do jogador
struct Jogador
{
    int id;
    char nome[100];
    int altura;
    int peso;
    char universidade[100];
    int anoNascimento;
    char cidadeNascimento[100];
    char estadoNascimento[100];
};

void imprime(struct Jogador jogador) // Função que imorime o jogador
{
    printf("[%d ## %s ## %d ## %d ## %d ## %s ## ", jogador.id, jogador.nome, jogador.altura,
           jogador.peso, jogador.anoNascimento, jogador.universidade);

    if (strlen(jogador.cidadeNascimento) == 0)
    {
        printf("nao informado");
    }
    else
    {
        printf("%s", jogador.cidadeNascimento);
    }

    printf(" ## ");

    if (strlen(jogador.estadoNascimento) == 0)
    {
        printf("nao informado");
    }
    else
    {
        printf("%s", jogador.estadoNascimento);
    }
}
// Main
int main()
{
    FILE *arq = fopen("/tmp/players.csv", "r");
    if (arq == NULL)
    {
        perror("Erro");
        exit(EXIT_FAILURE);
    }

    char linha[1000];
    fgets(linha, sizeof(linha), arq);

    struct Jogador *players = NULL;
    int num = 0;

    while (fgets(linha, sizeof(linha), arq))
    {
        struct Jogador jogador;
        sscanf(linha, "%d,%99[^,],%d,%d,%99[^,],%d,%99[^,],%99[^,]",
               &jogador.id, jogador.nome, &jogador.altura, &jogador.peso,
               jogador.universidade, &jogador.anoNascimento,
               jogador.cidadeNascimento, jogador.estadoNascimento);

        players = (struct Jogador *)realloc(players, (num + 1) * sizeof(struct Jogador));
        players[num] = jogador;
        num++;
    }

    char stringId[10];
    int id;
    while (1) // Chama a função que imprime o jogador
    {
        scanf("%s", stringId);
        if (strcmp(stringId, "FIM") == 0)
        {
            break;
        }
        id = atoi(stringId);

        int encontrou = 0;
        for (int i = 0; i < num; i++)
        {
            if (players[i].id == id)
            {
                imprime(players[i]);
                encontrou = 1;
                break;
            }
        }
    }

    free(players);
    fclose(arq);
    return 0;
}
