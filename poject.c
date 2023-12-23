#include<stdio.h>
#include<stdbool.h>
#include "converter.h" 

int main(){
    int choice ;
    int input ;
    char *binary ;
    char res ; 

    printf("\033[1;31m");
    puts("Welcome To Our Modest Project");
    printf("\033[0m"); 
    puts("now we have a few choices of converting numbers between the systems such as "); 
choices:  
    puts("1- converting from decimal to binary."); 
    puts("2- converting from binary to decimal."); 
    puts("3- converting from binary to hexadecimal."); 
    puts("4- converting from binary to octal."); 
    puts("5- converting from decimal to octal."); 
    puts("6- converting from decimal to hexadecimal."); 
    puts("7- converting from hexadecimal to octal."); 
    puts("8- converting from hexadecimal to binary."); 
    puts("9- converting from hexadecimal to decimal."); 
    puts("10- converting from octal to decimal."); 
    puts("11- converting from octal to hexadecimal."); 
    puts("12- converting from octal to binary."); 
x:  
    puts("enter the number of the wanted conversion [ 0 to end , -1 to repeat the choices ]: "); 
    scanf("%d" , &choice); 
    switch (choice)
    {   
    case -1:
        goto choices; 
    case 0:
        puts("do you really wnat to end the converting sys program ? "); 
        puts("Enter <y> to end or <n> to continue ");
        scanf(" %c" , &res); 
        if(res == 'y')
            break; 
        else if(res=='n')
            goto x; 
        else{
            puts("invalid input \n"); 
            goto x; 
        }
    case 1:
        puts("Enter an integer number  : "); 
        scanf("%d" , &input ); 
        Dec2bin(input);
        goto x ;  
    case 2: 
        puts("Enter the bits [until 64] \n<MAKE SURE THAT numbers are alaways represented in 2s complement> "); 
        scanf("%s" , binary); 
        printf("\tthe enterd binary is equivalent to %d in decimal \n" ,Bin2dec(binary) ); 
        goto x;     
    case 3: 
        puts("Enter the bits [until 64] \n<MAKE SURE THAT numbers are alaways represented in 2s complement> "); 
        scanf("%s" , binary); 
        printf("\tthe enterd Binary is equivalent to  " ); 
        Bin2hex(binary); 
        puts(" in Hexadecimal \n");
        goto x;     
    case 4: 
        puts("Enter the bits [until 64] \n<MAKE SURE THAT numbers are alaways represented in 2s complement> "); 
        scanf("%s" , binary); 
        printf("\tthe enterd Binary is equivalent to  " ); 
        Bin2oct(binary); 
        puts(" in Octal \n");
        goto x;               
    case 5: 
        puts("Enter the decimal number : "); 
        scanf("%d" , &input); 
        printf("\tthe enterd decimal is equivalent to  " ); 
        Dec2oct(input); 
        puts(" in Octal \n");
        goto x;     
    case 6: 
        puts("Enter the decimal number :"); 
        scanf("%d" , &input); 
        printf("\tthe enterd decimal is equivalent to  " ); 
        Dec2hex(input); 
        puts(" in Hexadecimal \n");
        goto x;     
    case 7: 
        puts("Enter the hexadecimal number <not preceded with 0x or 0X > :"); 
        scanf("%x" , &input); 
        printf("\tthe enterd hexadecimal is equivalent to  %o in Octal.\n" , input); 
        goto x;         
    case 8: 
        puts("Enter the hexadecimal number <not preceded with 0x or 0X > :"); 
        scanf("%X" , &input); 
        printf("\tthe enterd hexadecimal is equivalent to \n" ); 
        Dec2bin(input); 
        printf("\tin decimal and binary \n");
        goto x;     
    case 9: 
        puts("Enter the hexadecimal number <not preceded with 0x or 0X > :"); 
        scanf("%X" , &input); 
        printf("\tthe enterd hexadecimal is equivalent to %d in decimal \n" ,input); 
        goto x;         
    case 10: 
        puts("Enter the octal number :"); 
        scanf("%o" , &input); 
        printf("\tthe enterd octal is equivalent to %d in decimal \n" ,input); 
        goto x;        
    case 11: 
        puts("Enter the octal number :"); 
        scanf("%o" , &input); 
        printf("\tthe enterd octal is equivalent to %#X in hexadecimal \n" ,input); 
        goto x;         
    case 12: 
        puts("Enter the octal number :"); 
        scanf("%o" , &input); 
        printf("\tthe enterd octal is equivalent to " ); 
        Dec2bin(input); 
        printf("\tin decimal and binary \n");
        goto x;     
    default:
        puts("invalid choice \n"); 
        goto x;     
    }


    return 0; 
}
