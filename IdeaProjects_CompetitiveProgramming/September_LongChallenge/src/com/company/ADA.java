package com.company;

import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Arrays;
import java.util.Scanner;

public class ADA {

    static class Reader {
        final private int BUFFER_SIZE = 1 << 16;
        private DataInputStream din;
        private byte[] buffer;
        private int bufferPointer, bytesRead;

        public Reader()
        {
            din = new DataInputStream(System.in);
            buffer = new byte[BUFFER_SIZE];
            bufferPointer = bytesRead = 0;
        }

        public Reader(String file_name) throws IOException
        {
            din = new DataInputStream(new FileInputStream(file_name));
            buffer = new byte[BUFFER_SIZE];
            bufferPointer = bytesRead = 0;
        }

        public String readLine() throws IOException
        {
            byte[] buf = new byte[64]; // line length
            int cnt = 0, c;
            while ((c = read()) != -1)
            {
                if (c == '\n')
                    break;
                buf[cnt++] = (byte) c;
            }
            return new String(buf, 0, cnt);
        }

        public int nextInt() throws IOException
        {
            int ret = 0;
            byte c = read();
            while (c <= ' ')
                c = read();
            boolean neg = (c == '-');
            if (neg)
                c = read();
            do
            {
                ret = ret * 10 + c - '0';
            }  while ((c = read()) >= '0' && c <= '9');

            if (neg)
                return -ret;
            return ret;
        }

        public long nextLong() throws IOException
        {
            long ret = 0;
            byte c = read();
            while (c <= ' ')
                c = read();
            boolean neg = (c == '-');
            if (neg)
                c = read();
            do {
                ret = ret * 10 + c - '0';
            }
            while ((c = read()) >= '0' && c <= '9');
            if (neg)
                return -ret;
            return ret;
        }

        public double nextDouble() throws IOException
        {
            double ret = 0, div = 1;
            byte c = read();
            while (c <= ' ')
                c = read();
            boolean neg = (c == '-');
            if (neg)
                c = read();

            do {
                ret = ret * 10 + c - '0';
            }
            while ((c = read()) >= '0' && c <= '9');

            if (c == '.')
            {
                while ((c = read()) >= '0' && c <= '9')
                {
                    ret += (c - '0') / (div *= 10);
                }
            }

            if (neg)
                return -ret;
            return ret;
        }

        private void fillBuffer() throws IOException
        {
            bytesRead = din.read(buffer, bufferPointer = 0, BUFFER_SIZE);
            if (bytesRead == -1)
                buffer[0] = -1;
        }

        private byte read() throws IOException
        {
            if (bufferPointer == bytesRead)
                fillBuffer();
            return buffer[bufferPointer++];
        }

        public void close() throws IOException
        {
            if (din == null)
                return;
            din.close();
        }
    }

    public static void main(String[] args) throws java.lang.Exception {


        Reader sc=new Reader();
        long n=sc.nextLong();
        long k=sc.nextLong();
        long[] arr=new long[(int)n];
        for (int i=0;i<n;i++){
            arr[i]=sc.nextLong();
        }
        Arrays.sort(arr);

        long sum=0;
        for (int i=0;i<n;i++){
            sum=sum+arr[i];
        }
        long previousSum=sum;

        for (int i=0;i<k;i++){

             previousSum=sum;

            if (i<n/2){
                long previousSmallest=arr[i];
                arr[i]=arr[i]*2;
                sum=sum-previousSmallest+arr[i];


                long num=arr[(int)n-1-i];
                long previousLargest=num;
                arr[(int)n-1-i]=arr[(int)n-1-i]/2;
                if (num%2==1){
                    arr[(int)n-1-i]=arr[(int)n-1-i]+1;
                }
                sum=sum-previousLargest+arr[(int)n-1-i];

                if (sum<=previousSum){
                    //its oke its decreaasing
                    previousSum=sum;
                }else{
                    break;
                }

            }
            if ((i>=n/2 && n%2==1) || (i>n/2 && n%2==0)){
                Arrays.sort(arr);
                k=k-i;
                i=-1;
            }
        }


        System.out.println(previousSum);


    }
}
