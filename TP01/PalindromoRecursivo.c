#include <stdio.h>
#include <stdbool.h>
#include <string.h>

// Função recursiva para verificar se uma string é um palíndromo
bool palindromoRecursivo(const char* str, int inicio, int fim) {
    // Caso base: se o início for maior ou igual ao fim, a string é um palíndromo
    if (inicio >= fim) {
        return true;
    }

    // Verifica se os caracteres nas posições inicio e fim são iguais
    if (str[inicio] != str[fim]) {
        return false;
    }

    // Chamada recursiva para verificar o próximo par de caracteres
    return palindromoRecursivo(str, inicio + 1, fim - 1);
}

int main() {
    char str[100]; // Assumindo um tamanho máximo de 100 caracteres para a string
    while (fgets(str, sizeof(str), stdin) != NULL) {
        str[strcspn(str, "\n")] = '\0'; // Remover apenas a quebra de linha da entrada
        
        if (strcmp(str, "FIM") == 0) {
            break;
        }
        
        bool resp = palindromoRecursivo(str, 0, strlen(str) - 1);
        if (resp) {
            printf("SIM\n");
        } else {
            printf("NAO\n");
        }
    }

    return 0;
}
