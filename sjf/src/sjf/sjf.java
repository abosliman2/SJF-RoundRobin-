
package rr;

import java.util.Scanner;
import java.util.*;


public class sjf {
    
        public static void main(String args[])
        {
        Scanner input = new Scanner(System.in);
        System.out.println ("enter no of process:");
        int n = input.nextInt();
        int[] p = new int[n];
        int[] arrival_time = new int[n]; 
        int[] burst_time = new int[n];
        int[] complete_time = new int[n];
        int[] turn_around_time = new int[n];
        int[] wating_time = new int[n];
        int f[] = new int[n];
        int st=0, total=0;
        float avgwaiting_time=0, avgtotal=0;
 
for(int i=0;i<n;i++){
    System.out.println ("enter process " + (i+1) + " arrival time:");
    arrival_time[i] = input.nextInt();
    System.out.println ("enter process " + (i+1) + " brust time:");
    burst_time[i] = input.nextInt();
    p[i] = i+1;
    f[i] = 0;
    }
boolean a = true;
    while(true)
    {
    int c=n, min=999;
    if (total == n) 
    break;
    for (int i=0; i<n; i++)
    {

    if ((arrival_time[i] <= st) && (f[i] == 0) && (burst_time[i]<min)){
        min=burst_time[i];
        c=i;
             }
    }
    if (c==n)
        st++;
    else{
            complete_time[c]=st+burst_time[c];
            st+=burst_time[c];
            turn_around_time[c]=complete_time[c]-arrival_time[c];
            wating_time[c]=turn_around_time[c]-burst_time[c];
            f[c]=1;
            total++;
            }
        }
        System.out.println("\npid  arrival brust  complete turn waiting");
        for(int i=0;i<n;i++){
            avgwaiting_time+= wating_time[i];
            avgtotal+= turn_around_time[i];
            System.out.println(p[i]+"\t"+arrival_time[i]+"\t"+burst_time[i]+"\t"+complete_time[i]+"\t"+turn_around_time[i]+"\t"+wating_time[i]);
            }
            System.out.println ("\naverage tat is "+ (float)(avgtotal/n));
            System.out.println ("average wt is "+ (float)(avgwaiting_time/n));
            input.close();
            }
               }