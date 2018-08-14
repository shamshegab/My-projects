#include <stdio.h>
#include <stdlib.h>
#include <ctype.h>
#include <string.h>
#include <math.h>
#define MAXSIZE 100


typedef struct
{
    float items[MAXSIZE];
    int top;

} stack;

typedef struct Node
{
    char* key;
    float value;
    struct Node *next;

} Node;

typedef struct Map
{
    struct Node *head ;
    struct Node *tail ;
} Map;

void init(Map *linkedList)
{
    linkedList->head = linkedList->tail = NULL;

}

Node* newNode(char* key, float value)
{
    Node* n = (Node*) malloc(sizeof(Node));
    n->key = key;
    n->value = value;
    n->next = NULL;
    return n;
}

void put(Map *linkedList, char* key, float value)
{
    Node *n = newNode(key,value);
    n->next = linkedList->head;
    linkedList->head = n;
    if(!linkedList->tail)
    {
        linkedList->tail = n;
    }
}

float get(Map *m, char *key)
{
    Node *n = m->head;
    while(n)
    {
        if(!strcmp(n->key,key))
        {
            return n->value;
        }
        n = n->next;
    }
    return atof(key);
}

void initialize(stack* s)
{
    s->top=0;
}

void push(stack*s,float value)
{
    s->items[s->top]=value;
    s->top++;
}
float pop(stack*s)
{
    s->top--;
    return s->items[s->top];
}
float top(stack*s)
{
    return s->items[s->top-1];
}
int isempty(stack*s)
{
    if(s->top==0)
        return 1;
    else
        return 0;
}


float evaluatePostfix(char postfix[])
{
    int i,j;
    float m,x1,x2;
    stack a;
    initialize(&a);
    j = 0;
    char temp[20];

    for(i = 0; i < strlen(postfix); i++)
    {
        if (isdigit(postfix[i]))
        {
            while(postfix[i] !=',')
            {
                temp[j++] = postfix[i++];
            }
            temp[j] = '\0';
            j = 0;
            printf("%s\n\n", temp);

            push(&a, atof(temp));
        }
        else if(postfix[i] == '+' || postfix[i] == '-' || postfix[i] == '*' || postfix[i] == '/')
        {
            x2=pop(&a);
            x1=pop(&a);
            if(postfix[i]=='+')
                m=x1+x2;
            else if(postfix[i]=='-')
                m=x1-x2;
            else if(postfix[i]=='*')
                m=x1*x2;
            else if(postfix[i]=='/')
                m=x1/x2;
            push(&a,m);
        }
    }
    return pop(&a);
}


int isOperator(char ch)
{
    return ch == '*' || ch == '+' || ch == '/' || ch == '-';
}

int precedence(char x)
{
    if(x=='(')
        return 0;
    else if(x=='+'||x=='-')
        return 1;
    else if(x=='*'||x=='/'||x=='%')
        return 2;
    else
        return x;

}



int isOperand(char ch)
{
    return isdigit(ch) || isalpha(ch) || ch == '.';
}


int isValidFloat(char f[])
{
    int i;
    if(f[strlen(f)-1] == '.')
    {
        return 0;
    }
    int dotCounter = 0;
    for(i = 0; i < strlen(f); i++)
    {
        if(f[i] == '.')
        {
            dotCounter++;
        }
        if(dotCounter > 1)
        {
            return 0;
        }
    }
    return 1;
}

int isUnknownVar(Map *map, char ch[])
{
    Node *n = map->head;

    while(n)
    {
        if(!strcmp(ch, n->key))
        {
            return 0;
        }
        n = n->next;
    }
    return 1;
}


int infixToPostfix(Map *map, char infix[], char postfix[])
{
    stack s;
    char x;
    int i,j,t;
    initialize(&s);
    char temp[10];
    j=0;
    i = 0;

    while(i < strlen(infix))
    {
        if(infix[i] == 32)
        {
            i++;
        }

        else if(infix[i]=='(')
        {
            if(isOperand(infix[i-1]))
            {
                printf("Invalid Statement");
                return 0;
            }
            push(&s,'(');
            i++;
        }
        else if(infix[i]==')')
        {
            if(isOperand(infix[i+1]))
            {
                printf("Invalid Statement");
                return 0;
            }

            while((x=pop(&s))!='(')
                postfix[j++]=x;
            i++;
        }

        else if(isOperator(infix[i]))
        {
            if(isOperator(infix[i+1]) || isOperator(infix[i-1]))
            {
                printf("Invalid Statement");
                return 0;
            }
            while(precedence(infix[i])<=precedence(top(&s))&&!isempty(&s))
            {
                x=pop(&s);
                postfix[j++]=x;
            }
            push(&s,infix[i]);
            i++;
        }
        else if(isOperand(infix[i]))
        {
            t = 0;
            while(isOperand(infix[i]))
            {
                temp[t++] = infix[i++];
            }
            temp[t] = '\0';

            sprintf(temp, "%g", get(map, temp));
            int g;
            for(g = 0; g < strlen(temp); g++)
            {
                postfix[j++] = temp[g];
            }

            postfix[j++] = ',';
            //postfix[j] = '\0';

        }
        else
        {
            printf("Invalid Statement\n");
            return 0;
        }



    }

    while(!isempty(&s))
    {
        x=pop(&s);
        postfix[j++]=x;
    }


    postfix[j]='\0';

    printf("%s\n", postfix);

    return 1;
}

char* trim(char* str)
{
    char *str_new = malloc(sizeof(char));
    int end = 1;
    int begin = strlen(str);
    int i;
    for(i = strlen(str) - 1; i > 0; i--)
    {
        if(str[i] != ' ')
        {
            end = i+1;
            break;
        }
    }
    for(i = 0; i < strlen(str); i++)
    {
        if(str[i] != ' ')
        {
            begin = i;
            break;
        }
    }
    int j = 0;
    for(i = begin; i < end; i++)
    {
        str_new[j++] = str[i];
    }
    str_new[j++] = '\0';
    return str_new;
}



int validateVariable(char *a)
{
    int i;
    if(a==NULL)
    {
        return 0;
    }
    for (i=0; i<strlen(a); i++)
    {

        if(a[i]=='+'||a[i]=='-'||a[i]=='/'||a[i]=='*'||a[i]==' '||a[i]=='.'||isdigit(a[i]))
        {

            return 0;
        }

    }
    return 1;

}

int equalSign(char* exp)
{

    int i;
    int eq=0;
    for(i=0; i<strlen(exp); i++)
    {
        if(exp[i]=='=')
        {

            if(i==0||(i+1)==strlen(exp))
            {

                return 0;
            }
            else
            {
                eq++;
            }
        }
    }
    if(eq==1)
    {
        return 1;
    }
    return 0;
}

int ValidateInfix(char *a)
{
    int i;
    int open=0,closed=0;
    for(i=0; i<strlen(a); i++)
    {
        if(a[i]=='(')
        {
            open++;
        }
        else if(a[i]==')')
        {
            closed++;
        }
    }
    if(open!=closed)
    {
        return 0;
    }
    else
    {
        return 1;
    }
}

int main()
{

    Map  m;
    init(&m);
    char expression[50];
    char postFix[50];
    char *key;
    char *infix;
    float value;
    while(strcmp(expression, "end"))
    {
        printf("Enter the expression (or \"end\" to exit): ");
        gets(expression);
        if(!strcmp(expression, "end"))
        {
            exit(0);
        }
        if(equalSign(expression))   //contains equal sign and key not null and infix not null
        {
            key = strtok(expression, "=");
            infix = strtok(NULL, "=");
            key = trim(key);
        //infix = trim(infix);
            if(validateVariable(key))  //validate key
            {
                if(ValidateInfix(infix)&&infix != NULL) //check that infix not empty and num of open brakets = closed
                {

                    // Validation Complete All clear
                    if(infixToPostfix(&m, infix, postFix))
                    {
                        value = evaluatePostfix(postFix);
                        put(&m, key, value);
                        printf("%f", value);
                    }
                    printf("\n");
                }
                else
                {
                    printf("Invalid Statement\n");
                }

            }
            else
            {
                printf("Invalid Statement\n");
            }

        }
        else
        {
            printf("Invalid Statement\n");
        }

    }
    return 0;
}
