
package round_rr;
import java.util.ArrayList;
import java.util.Scanner;
public class Round_rr {
     public static void roundRobin(ArrayList<Integer> pId, ArrayList<Integer> arrivalTime, 
                                  ArrayList<Integer> burstTime, int n) 
    { 
        // result of average times 
        int res = 0; 
        int resc = 0; 
  
        // for sequence storage 
        String seq = new String(); 
  
        // copy the burst array and arrival array 
        // for not effecting the actual array 
        int res_b[] = new int[burstTime.size()]; 
        int res_a[] = new int[arrivalTime.size()]; 
  
        for (int i = 0; i < res_b.length; i++) { 
            res_b[i] = burstTime.get(i); 
            res_a[i] = arrivalTime.get(i); 
        } 
  
        // critical time of system 
        int t = 0; 
  
        // for store the waiting time 
        int waiting[] = new int[pId.size()]; 
  
        // for store the Turnaround time 
        int turnaround[] = new int[pId.size()]; 
  
        while (true) { 
            boolean flag = true; 
            for (int i = 0; i < pId.size(); i++) { 
  
                // these condition for if 
                // arrival is not on zero 
  
                // check that if there come before qtime 
                if (res_a[i] <= t) { 
                    if (res_a[i] <= n) { 
                        if (res_b[i] > 0) { 
                            flag = false; 
                            if (res_b[i] > n) { 
  
                                // make decrease the b time 
                                t = t + n; 
                                res_b[i] = res_b[i] - n; 
                                res_a[i] = res_a[i] + n; 
                                seq += "->" + pId.get(i); 
                            } 
                            else { 
  
                                // for last time 
                                t = t + res_b[i]; 
  
                                // store comp time 
                                turnaround[i] = t - arrivalTime.get(i); 
  
                                // store wait time 
                                waiting[i] = t - burstTime.get(i) - arrivalTime.get(i); 
                                res_b[i] = 0; 
  
                                // add sequence 
                                seq += "->" + pId.get(i); 
                            } 
                        } 
                    } 
                    else if (res_a[i] > n) { 
  
                        // is any have less arrival time 
                        // the coming process then execute them 
                        for (int j = 0; j < pId.size(); j++) { 
  
                            // compare 
                            if (res_a[j] < res_a[i]) { 
                                if (res_b[j] > 0) { 
                                    flag = false; 
                                    if (res_b[j] > n) { 
                                        t = t + n; 
                                        res_b[j] = res_b[j] - n; 
                                        res_a[j] = res_a[j] + n; 
                                        seq += "->" + pId.get(j); 
                                    } 
                                    else { 
                                        t = t + res_b[j]; 
                                        turnaround[j] = t - arrivalTime.get(j); 
                                        waiting[j] = t - burstTime.get(j) - arrivalTime.get(j); 
                                        res_b[j] = 0; 
                                        seq += "->" + pId.get(j); 
                                    } 
                                } 
                            } 
                        } 
  
                        // now the previous porcess according to 
                        // ith is process 
                        if (res_b[i] > 0) { 
                            flag = false; 
  
                            // Check for greaters 
                            if (res_b[i] > n) { 
                                t = t + n; 
                                res_b[i] = res_b[i] - n; 
                                res_a[i] = res_a[i] + n; 
                                seq += "->" + pId.get(i); 
                            } 
                            else { 
                                t = t + res_b[i]; 
                                turnaround[i] = t - arrivalTime.get(i); 
                                waiting[i] = t - burstTime.get(i) - arrivalTime.get(i); 
                                res_b[i] = 0; 
                                seq += "->" + pId.get(i); 
                            } 
                        } 
                    } 
                } 
  
                // if no process is come on thse critical 
                else if (res_a[i] > t) { 
                    t++; 
                    i--; 
                } 
            } 
            // for exit the while loop 
            if (flag) { 
                break; 
            } 
        } 
  
        System.out.println("Process id  Turnaround Time  Waiting Time"); 
        for (int i = 0; i < pId.size(); i++) { 
            System.out.println("    " + pId.get(i) + "        " + turnaround[i] 
                               + "        " + waiting[i]); 
  
            res = res + waiting[i]; 
            resc = resc + turnaround[i]; 
        } 
  
        System.out.println("Average waiting time is "
                           + (float)res / pId.size()); 
        System.out.println("Average compilation  time is "
                           + (float)resc / pId.size()); 
        System.out.println("Gantt Chart :  " + seq); 
    } 
  
    // Driver Code 
    public static void main(String args[]) 
    { 
        Scanner sc = new Scanner(System.in);
        // name of the process 
        ArrayList<Integer> pId = new ArrayList<>(); 
  
        // arrival for every process 
        ArrayList<Integer> arrivalTime = new ArrayList<>();
        
        // burst time for every process 
        ArrayList<Integer> burstTime = new ArrayList<>();
         
        // quantum time of each process 
        int q;
        
        System.out.println("Enter the quantum size : ");
        q = sc.nextInt();
        
        System.out.println("Enter the process id, arrival time and burst time : ");
        System.out.println("Enter 0, 0, 0 to terminate.");
        int id, at, bt;
        do {
            System.out.println();
            id = sc.nextInt();
            at = sc.nextInt();
            bt = sc.nextInt();
            if(id == 0 && at == 0 && bt == 0) {
                break;
            }
            pId.add(id);
            arrivalTime.add(at);
            burstTime.add(bt);
        }while(true);
        
  
        // cal the function for output 
        roundRobin(pId, arrivalTime, burstTime, q);
        sc.close();
    } 
    
}
