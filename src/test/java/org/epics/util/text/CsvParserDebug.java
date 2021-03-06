/**
 * Copyright (C) 2012-14 epics-util developers. See COPYRIGHT.TXT
 * All rights reserved. Use is subject to license terms. See LICENSE.TXT
 */

package org.epics.util.text;

import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.swing.JFileChooser;
import static org.epics.util.text.StringUtil.DOUBLE_REGEX_WITH_NAN;

/**
 *
 * @author carcassi
 */
public class CsvParserDebug {
    public static void main(String[] args) throws Exception {
        JFileChooser fc = new JFileChooser();
        int result = fc.showOpenDialog(null);
        if (result == JFileChooser.APPROVE_OPTION) {
            File selectedFile = fc.getSelectedFile();
            CsvParserResult parseResult = CsvParser.AUTOMATIC.parse(new FileReader(selectedFile));
            for (int i = 0; i < parseResult.getColumnNames().size(); i++) {
                String columnName = parseResult.getColumnNames().get(i);
                Class<?> columnType = parseResult.getColumnTypes().get(i);
                if (columnType == String.class) {
                    System.out.println(columnName + " - " + columnType + " - " + nonNumberMatching((List<String>) parseResult.getColumnValues().get(i)));
                } else {
                    System.out.println(columnName + " - " + columnType);
                }
            }
        }
    }
    
    public static List<String> nonNumberMatching(List<String> strings) {
        Matcher matcher = Pattern.compile(DOUBLE_REGEX_WITH_NAN).matcher("");
        List<String> nonMatching = new ArrayList<>();
        for (String string : strings) {
            if (!matcher.reset(string).matches()) {
                nonMatching.add(string);
            }
        }
        return nonMatching;
    }
}
