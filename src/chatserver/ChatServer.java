/*
 * Copyright (C) 2015 wolf
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package chatserver;

/**
 *
 * @author wolf
 */
import java.io.*;
import java.net.*;

import java.util.Scanner;

public class ChatServer {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        try{
        ServerSocket serverSocket = new ServerSocket(9980);
        Socket clientSocket = serverSocket.accept();
        PrintWriter out = new PrintWriter(clientSocket.getOutputStream(),true);
        hammingCoder hammingcoder = new hammingCoder();
        Scanner in = new Scanner(clientSocket.getInputStream());
        out.println("Welcome to chat server 1");
        while(true){
            
            if(in.hasNext()){
                hammingcoder.decode(in.nextLine());
                //System.out.println(in.nextLine());
                    
            }
            
        }
        } catch (Exception e){
            System.out.println(e.getMessage());
            e.printStackTrace();
        }
        }   
}
    
