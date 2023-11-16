#include <stdio.h>
#include <stdbool.h>
#include <string.h>

bool palindromo(const char* str) {
    int len = strlen(str);
    
    for (int i = 0; i < len / 2; i++) {
        if (str[i] != str[len - i - 1]) {
            return false;
        }
    }
    return true;
}

int main() {
    char str[100]; // Assumindo um tamanho máximo de 100 caracteres para a string
    while (fgets(str, sizeof(str), stdin) != NULL) {
        str[strcspn(str, "\n")] = '\0'; // Remover apenas a quebra de linha da entrada
        
        if (strcmp(str, "FIM") == 0) {
            break;
        }
        
        bool resp = palindromo(str);
        if (resp) {
            printf("SIM\n");
        } else {
            printf("NAO\n");
        }
    }

    return 0;
}
