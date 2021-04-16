#include <algorithm>
#include <iostream>
#include <fstream>
#include <sstream>
#include <iomanip>
#include <openssl/sha.h>

#define KEY_SIZE 0x10

using namespace std;

string calc_id0(char *data, int size){
    unsigned char hash[SHA256_DIGEST_LENGTH];
    SHA256_CTX sha_ctx;
    SHA256_Init(&sha_ctx);
    SHA256_Update(&sha_ctx, data, size);
    SHA256_Final(hash, &sha_ctx);

    for(int i = 0; i < 4; i++){
        reverse(hash + i * 4, hash + i * 4 + 4);
    }

    stringstream ss;
    for(int i = 0; i < 0x10; i++){
        ss << uppercase << hex << setw(2) << setfill('0') << (int)hash[i];
    }
    return ss.str();
}

int main(){
    ifstream ifs("movable.sed", ios::binary);

    if(!ifs)
        return 1;

    ifs.seekg(0x110, ios::beg);
    char *buf = new char[KEY_SIZE];
    ifs.read(buf, KEY_SIZE);
    ifs.close();

    cout << calc_id0(buf, KEY_SIZE) << endl;
    delete buf;
    return 0;
}
