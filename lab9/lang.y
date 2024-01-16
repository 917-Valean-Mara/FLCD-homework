%{
#include <stdio.h>
#include <stdlib.h>
#include <string.h>
%}

%token IF
%token ELSE
%token FOR
%token WHILE
%token READ
%token PRINT
%token START
%token END
%token INT
%token VAR
%token STRING

%token PLUS
%token MINUS
%token TIMES
%token DIVIDE
%token MOD

%token LESS
%token LESS_OR_EQUAL
%token GREATER
%token GREATER_OR_EQUAL
%token EQUAL
%token DIFFERENT

%token ASSIGN

%token LEFT_CURLY
%token RIGHT_CURLY
%token LEFT_PAREN
%token RIGHT_PAREN
%token LEFT_BRACKET
%token RIGHT_BRACKET
%token COLON
%token SEMICOLON
%token COMMA
%token APOSTROPHE
%token QUOTE
%token SPACE
%token DOT

%token IDENTIFIER
%token NUMBER_CONST
%token STRING_CONST

%start program

%%

program : START declaration_list compound_statement END

declaration_list : declaration | declaration SEMICOLON declaration_list

declaration : simple_declaration | array_declaration

simple_declaration : VAR IDENTIFIER COLON type SEMICOLON
array_declaration : VAR IDENTIFIER LEFT_BRACKET NUMBER_CONST RIGHT_BRACKET COLON type SEMICOLON

type : INT | STRING

compound_statement : statement | statement compound_statement

statement : assign_statement | if_statement | while_statement | for_statement | read_statement | print_statement

print_statement : PRINT LEFT_PAREN term RIGHT_PAREN SEMICOLON

term : identifier | constant

read_statement : READ LEFT_PAREN identifier RIGHT_PAREN SEMICOLON

for_statement : FOR LEFT_PAREN for_condition RIGHT_PAREN statement_list

for_condition : assign_statement condition SEMICOLON assign_statement

while_statement : WHILE LEFT_PAREN condition RIGHT_PAREN statement_list

assign_statement : identifier ASSIGN expression SEMICOLON

condition : expression relational_operator expression

relational_operator : LESS | LESS_OR_EQUAL | GREATER | GREATER_OR_EQUAL | EQUAL | DIFFERENT

expression : term | term arithmetic_operator term

arithmetic_operator : PLUS | MINUS | TIMES | DIVIDE | MOD

statement_list : LEFT_CURLY compound_statement RIGHT_CURLY

if_statement : IF LEFT_PAREN condition RIGHT_PAREN statement_list | if_statement else_statement

else_statement : ELSE statement_list

identifier : IDENTIFIER

constant : constint | conststring

constint : NUMBER_CONST

conststring : STRING_CONST

%%

void yyerror(char *s) {
    fprintf(stderr, "%s\n", s);
    exit(EXIT_FAILURE);
}

int yylex(void);

extern FILE *yyin;

int main(int argc, char *argv[]) {
    if (argc > 1) {
        yyin = fopen(argv[1], "r");
        if (!yyin) {
            fprintf(stderr, "Could not open specified file: %s\n", argv[1]);
            exit(EXIT_FAILURE);
        }
    }

    if (yyparse() == 0) {
        printf("Program is syntactically correct.\n");
        return 0;
    } else {
        printf("Program is NOT syntactically correct.\n");
        return 1;
    }
}
