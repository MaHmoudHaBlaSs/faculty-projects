#include<stdio.h>
#include<string.h>

void Dec2bin(int value); 
void Dec2hex(int value);
void Bin2hex(char *bits); 
int Bin2dec(char *arr);
void Bin2oct(char *bits); 
void Dec2oct(int value ); 

void Dec2bin( int value)
{
    unsigned int displayMask = 1 << 31;
    printf("%10d = ", value);
    for (unsigned short c = 1; c <= 32; ++c)
    {
        putchar(value & displayMask ? '1' : '0');
        value <<= 1;
        if (c % 8 == 0)
            putchar(' ');
    }
    putchar('\n');
}

char* negative_converter(char *bits) {
  bool detector = false;
  for (int i = strlen(bits) - 1; i >= 0; --i) {
    if (!detector) {
      if (bits[i] == '1') {
        detector = true;
        bits[i] = '1';
      } else {
        bits[i] = '0';
      }
    } else {
      bits[i] ^= 1;
    }
  }
  return bits;
}
int Bin2dec(char *arr ){
    // check if it's a negative number 
    bool ngtv = false ; 
    if( *arr == '1'){
      arr = negative_converter(arr);
      ngtv =  true ; 
    }

    unsigned int weight = 1; 
    int result =0; 
    for(int i = strlen(arr)-1 ; i>=0 ;i--){
        if(arr[i] == '1')
            result += weight ; 
        weight *= 2; 
    }
    return ngtv ? -result : result ; 
}
void Bin2hex(char *bits){
    Dec2hex(Bin2dec(bits)); // binary --> decimal --> hexa 
} 
void Dec2hex(int value){
    printf("%#X",value  ); 
}
void Bin2oct(char *bits){
    Dec2oct(Bin2dec(bits)); 
} 
void Dec2oct(int value ){
    printf("%o" , value ); 
}

