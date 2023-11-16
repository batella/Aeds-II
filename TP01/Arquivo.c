#include <stdio.h>

double fmod(double x, double y)
{
    return x - (int)(x / y) * y;
}

void manipulaArq(int pos)
{
    FILE *arquivozin = fopen("numeros.txt", "r");

    int Inteiro;
    double real;
    char enter;

    fseek(arquivozin, 0, SEEK_END);

    int tamanhoArq = ftell(arquivozin);

    fseek(arquivozin, pos, SEEK_SET);
    fscanf(arquivozin, "%lf", &real);
    fseek(arquivozin, pos, SEEK_SET); // retorna o ponteiro para a posição original

    for (int i = 0; i < tamanhoArq; i++) // teste se existe quebra de linha
    {
        fscanf(arquivozin, "%c", &enter);
        pos++;
        if (enter == '\n' || enter == EOF)
        {
            i = tamanhoArq;
        }
    }

    // chama a recursão para começar a printar pelo primeiro
    if (pos < tamanhoArq)
    {
        manipulaArq(pos);
    }

    if (real == (int)real)
    {
        Inteiro = (int)real;
        printf("%d\n", Inteiro);
    }
    else
    {
        if (fmod((real * 10), 1) == 0)
        {
            printf("%.1f\n", real);
        }
        else if (fmod((real * 100), 1) == 0)
        {
            printf("%.2f\n", real);
        }
        else
        {
            printf("%.3f\n", real);
        }
    }

    fclose(arquivozin);
}

int main(void)
{
    int tamanho = 0;
    double numeroLido;

    scanf("%d", &tamanho);

    FILE *escrita = fopen("numeros.txt", "w");

    for (int i = 0; i < tamanho; i++)
    {
        scanf("%lf", &numeroLido);
        fprintf(escrita, "%lf\n", numeroLido);
    }

    fclose(escrita);

    manipulaArq(0);

    return 0;
}