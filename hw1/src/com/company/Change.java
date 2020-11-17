package com.company;


import java.util.ArrayList;
import java.util.HashMap;

public class Change {
    public static void Output(HashMap<Long, Long> result, ArrayList<Long> nominals){
        System.out.print(" Комбинация: ");
        for (int iter=0;iter<nominals.size(); iter++){
            long iter_el = nominals.get(iter);
            long volume = result.get(iter_el);
            if (volume % 10 == 1){
                System.out.print(volume +" купюра номиналом "+iter_el);
            }else if(volume % 10 >= 5 || volume % 10 == 0){
                System.out.print(volume +" купюр номиналом "+iter_el);
            }else{
                System.out.print(volume +" купюры номиналом "+iter_el);
            }
            if(iter+1<nominals.size()){
                System.out.print(", ");
            }
            //System.out.print("Номиналом "+iter_el+ " по "+ volume +" раз + ");
        }
        System.out.println();
    }


    public static long count(Long sum, ArrayList<Long> nominals, HashMap<Long, Long> result,int iter, long unicle) {

        unicle=0;
        if (nominals.size() > iter) {
            Long iter_el = nominals.get(iter);

            for (Long i = 0L; i <= (sum / iter_el); i++) {
                Long new_sum = sum - iter_el * i;
                if (new_sum == 0) {
                    result.put(iter_el, i);
                    //System.out.println(result);
                    unicle++;
                    //System.out.print(unicle);
                    Output(result, nominals);

                } else if (new_sum < 0) {
                   // System.out.println("хрень");
                    unicle= 0;
                } else {
                    //System.out.println(5);
                    result.put(iter_el, i);

                    iter++;
                    unicle+=count(new_sum, nominals, result,iter,unicle);
                    //System.out.println("unicle"+unicle);
                }
                //System.out.println("__________________"+iter);
                iter--;
                result.put(iter_el, 0L);
            }
        }

    return unicle;
    }
}