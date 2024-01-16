
/* A Bison parser, made by GNU Bison 2.4.1.  */

/* Skeleton interface for Bison's Yacc-like parsers in C
   
      Copyright (C) 1984, 1989, 1990, 2000, 2001, 2002, 2003, 2004, 2005, 2006
   Free Software Foundation, Inc.
   
   This program is free software: you can redistribute it and/or modify
   it under the terms of the GNU General Public License as published by
   the Free Software Foundation, either version 3 of the License, or
   (at your option) any later version.
   
   This program is distributed in the hope that it will be useful,
   but WITHOUT ANY WARRANTY; without even the implied warranty of
   MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
   GNU General Public License for more details.
   
   You should have received a copy of the GNU General Public License
   along with this program.  If not, see <http://www.gnu.org/licenses/>.  */

/* As a special exception, you may create a larger work that contains
   part or all of the Bison parser skeleton and distribute that work
   under terms of your choice, so long as that work isn't itself a
   parser generator using the skeleton or a modified version thereof
   as a parser skeleton.  Alternatively, if you modify or redistribute
   the parser skeleton itself, you may (at your option) remove this
   special exception, which will cause the skeleton and the resulting
   Bison output files to be licensed under the GNU General Public
   License without this special exception.
   
   This special exception was added by the Free Software Foundation in
   version 2.2 of Bison.  */


/* Tokens.  */
#ifndef YYTOKENTYPE
# define YYTOKENTYPE
   /* Put the tokens into the symbol table, so that GDB and other debuggers
      know about them.  */
   enum yytokentype {
     IF = 258,
     ELSE = 259,
     FOR = 260,
     WHILE = 261,
     READ = 262,
     PRINT = 263,
     START = 264,
     END = 265,
     INT = 266,
     VAR = 267,
     STRING = 268,
     PLUS = 269,
     MINUS = 270,
     TIMES = 271,
     DIVIDE = 272,
     MOD = 273,
     LESS = 274,
     LESS_OR_EQUAL = 275,
     GREATER = 276,
     GREATER_OR_EQUAL = 277,
     EQUAL = 278,
     DIFFERENT = 279,
     ASSIGN = 280,
     LEFT_CURLY = 281,
     RIGHT_CURLY = 282,
     LEFT_PAREN = 283,
     RIGHT_PAREN = 284,
     LEFT_BRACKET = 285,
     RIGHT_BRACKET = 286,
     COLON = 287,
     SEMICOLON = 288,
     COMMA = 289,
     APOSTROPHE = 290,
     QUOTE = 291,
     SPACE = 292,
     DOT = 293,
     IDENTIFIER = 294,
     NUMBER_CONST = 295,
     STRING_CONST = 296
   };
#endif
/* Tokens.  */
#define IF 258
#define ELSE 259
#define FOR 260
#define WHILE 261
#define READ 262
#define PRINT 263
#define START 264
#define END 265
#define INT 266
#define VAR 267
#define STRING 268
#define PLUS 269
#define MINUS 270
#define TIMES 271
#define DIVIDE 272
#define MOD 273
#define LESS 274
#define LESS_OR_EQUAL 275
#define GREATER 276
#define GREATER_OR_EQUAL 277
#define EQUAL 278
#define DIFFERENT 279
#define ASSIGN 280
#define LEFT_CURLY 281
#define RIGHT_CURLY 282
#define LEFT_PAREN 283
#define RIGHT_PAREN 284
#define LEFT_BRACKET 285
#define RIGHT_BRACKET 286
#define COLON 287
#define SEMICOLON 288
#define COMMA 289
#define APOSTROPHE 290
#define QUOTE 291
#define SPACE 292
#define DOT 293
#define IDENTIFIER 294
#define NUMBER_CONST 295
#define STRING_CONST 296




#if ! defined YYSTYPE && ! defined YYSTYPE_IS_DECLARED
typedef int YYSTYPE;
# define YYSTYPE_IS_TRIVIAL 1
# define yystype YYSTYPE /* obsolescent; will be withdrawn */
# define YYSTYPE_IS_DECLARED 1
#endif

extern YYSTYPE yylval;


