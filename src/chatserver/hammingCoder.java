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

import static java.nio.charset.StandardCharsets.UTF_8;
import java.util.ArrayList;
import java.util.Arrays;

/**
 *
 * @author wolf
 */
public class hammingCoder {

    public hammingCoder() {
    }

    ;
    
    public ArrayList encode(String a) {
        ArrayList<int[]> hammingCodes = new ArrayList<int[]>();
        char[] bin;
        int tempHamming[] = new int[0];
        byte binData[] = a.getBytes(UTF_8);
        System.out.println(Arrays.toString(binData));
        for (int i = 0; i < binData.length; i++) {
            System.out.print(Integer.toBinaryString(binData[i]));
            System.out.println();
            String b = Integer.toBinaryString(binData[i]);
            a = new StringBuilder(b).reverse().toString();
            int r = 0;
            int m = a.length();
            while ((m + r + 1) >= Math.pow(2, r)) {
                r++;
            }
            System.out.println("Amount of parity bits: " + r);
            for (int x = 0; x < a.length(); x++) {
                tempHamming = new int[r + a.length()];
                for (int z = 0; z < tempHamming.length; z++) {
                    if (z == 0 || z == 1 || (z + 1 & z) == 0) {
                        tempHamming[z] = 9;
                    } else {
                        tempHamming[z] = 2;
                    }
                }

                //System.out.print(a.charAt(x));
                boolean done = false;
                int y;
                for (int z = 0; z < a.length(); z++) {
                    for (int d = 0; d < tempHamming.length; d++) {
                        if (tempHamming[d] == 2) {
                            tempHamming[d] = Character.getNumericValue(a.charAt(z));
                            break;
                        }
                    }
                }
//                while (done == false) {
////                    // for sanity make y = x+1 as arrays start at 0 x will = one
////                    //less than it would if you didnt count from 0

            }
            System.out.println();
            int ones, zeros;

            for (int z = 0; z < tempHamming.length; z++) {

                if (((z + 1) & z) == 0 && z + 1 > 0) {
                    zeros = 0;
                    ones = 0;
                    //hamming code check for bit zero 
                    if (z == 0) {
                        System.out.println("Current z : " + z);
                        for (int d = 2; d < tempHamming.length; d += 2) {
                            if (tempHamming[d] == 0) {
                                zeros++;
                            }
                            if (tempHamming[d] == 1) {
                                ones++;
                            }
                        }
                    }
                    // check if 1s or zeros are even
                    if (ones % 2 == 0) {
                        tempHamming[z] = 0;
                    } else {
                        tempHamming[z] = 1;
                    }
                    //Hamming for bit 1
                    if (z == 1) {
                        int howMany = 0;
                        for (int d = 1; d < tempHamming.length; d++) {
                            if (tempHamming[d] == 0) {
                                zeros++;
                            }
                            if (tempHamming[d] == 1) {
                                ones++;
                            }
                            howMany++;
                            if (howMany >= 2) {
                                d += 2;
                            }
                        }
                        if (ones % 2 == 0) {
                            tempHamming[z] = 0;
                        } else {
                            tempHamming[z] = 1;
                        }
                    }
                    if (z == 3) {
                        int howMany = 0;
                        for (int d = 3; d < tempHamming.length; d++) {
                            if (tempHamming[d] == 0) {
                                zeros++;
                            }
                            if (tempHamming[d] == 1) {
                                ones++;
                            }
                            howMany++;
                            if (howMany >= 4) {
                                d += 4;
                            }
                        }
                        if (ones % 2 == 0) {
                            tempHamming[z] = 0;
                        } else {
                            tempHamming[z] = 1;
                        }
                    }
                    if (z == 7) {
                        int howMany = 0;
                        for (int d = 7; d < tempHamming.length; d++) {
                            if (tempHamming[d] == 0) {
                                zeros++;
                            }
                            if (tempHamming[d] == 1) {
                                ones++;
                            }
                            howMany++;
                            if (howMany >= 8) {
                                d += 8;
                            }
                        }
                        if (ones % 2 == 0) {
                            tempHamming[z] = 0;
                        } else {
                            tempHamming[z] = 1;
                        }
                    }

                }
//               System.out.println("");
//                for (int d = 0; d < tempHamming.length; d++) {
//                    System.out.print(tempHamming[d]);
//                }

            }
            hammingCodes.add(tempHamming);
          //  System.out.println("IMHERE!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!");
        }
        
//        System.out.println();
        return hammingCodes;
    }
    public void decode(String a){
        // check bits
        int zeros;
        int ones;
        for (int i = 0;i < a.length();i++){
            if (((i + 1) & i) == 0 && i + 1 > 0) {
                    zeros = 0;
                    ones = 0;
                    //hamming code check for bit zero 
                    if (i == 0) {
                        for (int d = 0; d < a.length(); d += 2) {
                            if (a.charAt(i) == 0) {
                                zeros++;
                            }
                            if (a.charAt(i) == 1) {
                                ones++;
                            }
                        }
                    }
                    // check if 1s or zeros are even
                    if (!(ones % 2 == 0)) {
                        System.out.print("ERROR");
                    }   if (!(zeros % 2 == 0)) {
                        System.out.print(" Zeros ERROR");
                    } 
                    //Hamming for bit 1
//                    if (z == 1) {
//                        int howMany = 0;
//                        for (int d = 1; d < tempHamming.length; d++) {
//                            if (tempHamming[d] == 0) {
//                                zeros++;
//                            }
//                            if (tempHamming[d] == 1) {
//                                ones++;
//                            }
//                            howMany++;
//                            if (howMany >= 2) {
//                                d += 2;
//                            }
//                        }
//                        if (ones % 2 == 0) {
//                            tempHamming[z] = 0;
//                        } else {
//                            tempHamming[z] = 1;
//                        }
//                    }
//                    if (z == 3) {
//                        int howMany = 0;
//                        for (int d = 3; d < tempHamming.length; d++) {
//                            if (tempHamming[d] == 0) {
//                                zeros++;
//                            }
//                            if (tempHamming[d] == 1) {
//                                ones++;
//                            }
//                            howMany++;
//                            if (howMany >= 4) {
//                                d += 4;
//                            }
//                        }
//                        if (ones % 2 == 0) {
//                            tempHamming[z] = 0;
//                        } else {
//                            tempHamming[z] = 1;
//                        }
//                    }
//                    if (z == 7) {
//                        int howMany = 0;
//                        for (int d = 7; d < tempHamming.length; d++) {
//                            if (tempHamming[d] == 0) {
//                                zeros++;
//                            }
//                            if (tempHamming[d] == 1) {
//                                ones++;
//                            }
//                            howMany++;
//                            if (howMany >= 8) {
//                                d += 8;
//                            }
//                        }
//                        if (ones % 2 == 0) {
//                            tempHamming[z] = 0;
//                        } else {
//                            tempHamming[z] = 1;
//                        }
               }

                }
        //is 0s and 1s even
        // if not try correct
        //if more than one error detected
        //ask to resend.
        
        
    
    
    }

}
