/*
 * =====================================================================================
 *
 *       Filename:  list.c
 *
 *    Description:  for c list learn
 *
 *        Version:  1.0
 *        Created:  2015年09月09日 14时12分59秒
 *       Revision:  none
 *       Compiler:  gcc
 *
 *         Author:  YOUR NAME (), 
 *   Organization:  个人玩的
 *
 * =====================================================================================
 */

#include <stdio.h>
#include <stdlib.h>
#define LENGTH 10
struct node{
   struct node *next;
   int value;
};
struct node* create_link(struct node *start,struct node *ptr,int num){
	int i=0;
	for(i = 0; i < num; i++){
		struct node *ptr_next;	
		ptr_next = (struct node*)malloc(sizeof(struct node));
		printf("please enter data:\n");
		scanf("%d",&ptr_next->value);
		ptr_next->next = NULL;
		if(start == NULL) start = ptr_next;
		if(ptr==NULL){
			ptr = ptr_next;
		}
		if(ptr != NULL){
			ptr->next = ptr_next;
			ptr = ptr->next;
		}
	}
	return start;
}
void print_link(struct node *ptr){
	while(ptr != NULL){
		printf("value is %d\n",ptr->value);
		ptr = ptr->next;
	}
}
int main(int argc, char *argv[]){
	struct node *ptr = NULL;
	struct node *start = NULL;
	start = create_link(start,ptr,10);
	print_link(start);
	return 0;
}

