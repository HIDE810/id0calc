#include <stdio.h>
#include <openssl/sha.h>

#define swap(a, b) a^=b^=a^=b

void calc_id0(char *data, int size){
    unsigned char hash[SHA256_DIGEST_LENGTH];
    SHA256_CTX sha_ctx;
    SHA256_Init(&sha_ctx);
    SHA256_Update(&sha_ctx, data, size);
    SHA256_Final(hash, &sha_ctx);

    for(int i = 0; i < 4; i++){
        swap(hash[i*4], hash[i*4+3]);
        swap(hash[i*4+1], hash[i*4+2]);
    }

    for(int i = 0; i < 0x10; i++){
        printf("%02X", hash[i]);
    }
    printf("\n");
}

int main(){
    char buf[0x10];
    FILE *fp = fopen("movable.sed", "rb");

    if(!fp)
        return 1;

    fseek(fp, 0x110, SEEK_SET);
    fread(&buf, sizeof(char), sizeof(buf), fp);
    fclose(fp);

    calc_id0(buf, sizeof(buf));
    return 0;
}
