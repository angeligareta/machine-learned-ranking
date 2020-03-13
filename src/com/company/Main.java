package com.company;

import com.company.preprocess.Preprocess;

import java.io.IOException;

public class Main {
    private final static String Dataset1PathNoExt = "./data/dataset_1";
    private final static String Dataset2PathNoExt = "./data/dataset_2";
    private final static String Dataset3PathNoExt = "./data/dataset_3";

    private final static String[] QueryWords1 = new String[]{"white", "blood", "cells", "count"};
    private final static String[] QueryWords2 = new String[]{"glucose", "blood"};
    private final static String[] QueryWords3 = new String[]{"bilirubin", "blood"};

    public static void main(String[] args) {
        try {
            Preprocess preprocess = new Preprocess();
            preprocess.preprocessDataset(Dataset1PathNoExt + ".csv", Dataset1PathNoExt + "_modified.csv", QueryWords1);
            preprocess.preprocessDataset(Dataset2PathNoExt + ".csv", Dataset2PathNoExt + "_modified.csv", QueryWords2);
            preprocess.preprocessDataset(Dataset3PathNoExt + ".csv", Dataset3PathNoExt + "_modified.csv", QueryWords3);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
