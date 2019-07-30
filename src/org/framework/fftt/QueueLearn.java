package org.framework.fftt;

import java.util.Collection;
import java.util.Iterator;
import java.util.Queue;
import java.util.Stack;

/**
 * Created with IntelliJ IDEA.
 * User: lixp
 * Date: 2019/7/30
 * Time: 14:57
 * Mail: lixp0915@163.com
 */
public class QueueLearn {

    public static Stack<Integer> sta = new Stack<>();
    public static Stack<Integer> max_sta = new Stack<>();

    public static void push(Integer i ) {
        if(sta.empty()){
            sta.push(i);
            max_sta.push(i);
        }
        if(!sta.empty()){
            sta.push(i);
            if(!max_sta.empty()) {
                if(i >= max_sta.peek()){
                    max_sta.push(i);
                }else{
                    int tmp = max_sta.peek();
                    max_sta.pop();
                    max_sta.push(i);
                    max_sta.push(tmp);
                }
            }
        }
    }

    public static Integer max() {
        if(max_sta.empty()) {
            return null;
        }else {
            return max_sta.peek();
        }
    }
    public static void main(String[] args) {
        QueueLearn.push(4);
        QueueLearn.push(7);
        QueueLearn.push(10);
        QueueLearn.push(5);
        System.out.print("max value is :" + QueueLearn.max());
    }
}
