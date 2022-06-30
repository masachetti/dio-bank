package utils;

import org.apache.commons.lang3.StringUtils;

import java.util.*;

/*
Maven dependencies
<dependency>
    <groupId>org.apache.commons</groupId>
    <artifactId>commons-lang3</artifactId>
    <version>3.12.0</version>
</dependency>
*/
public class TablePrinter {
    public static void printTable(List<String> headerList, List<List<String>> tableContent, int padding){
        // Verificação inicial
        int numberOfColumns = headerList.size();
        tableContent.forEach((row)->{
            if (row.size() != numberOfColumns)
                throw new IllegalArgumentException("All rows in tableContent must have the same size of headerList");
        });

        // Criação dos string builders
        StringBuilder headerBuilder = new StringBuilder();
        List<StringBuilder> rowListBuilder = new ArrayList<>(){{
            for (int i = 0; i < tableContent.size(); i++) { add(new StringBuilder());} // Rows
        }};

        for (int i = 0; i < numberOfColumns; i++) {
            // Calculo do maior tamanho
            Set<Integer> cellValueSizes = new HashSet<>();
            cellValueSizes.add(headerList.get(i).length());
            int finalI = i;
            tableContent.forEach(row->{
                cellValueSizes.add(row.get(finalI).length());
            });
            int maxSize = Collections.max(cellValueSizes);
            int cellSize = maxSize + padding;

            // Popular os string builders
            headerBuilder.append(StringUtils.center(headerList.get(i), cellSize));
            for (int j = 0; j < tableContent.size(); j++) {
                List<String> row = tableContent.get(j);
                rowListBuilder.get(j).append(StringUtils.center(row.get(i), cellSize));
            }
        }
        // Criação da linha
        String line = StringUtils.repeat("-", headerBuilder.length());
        // Processo de print
        System.out.println(line);
        System.out.println(headerBuilder.toString());
        System.out.println(line);
        rowListBuilder.forEach(row->{
            System.out.println(row.toString());
        });
        System.out.println(line);
    }
}
