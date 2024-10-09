package helper.java.helpers;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import java.util.Iterator;

public class ExcelTest {


    public static String getNextRow(XSSFSheet sheet, String cellContent) {
        Iterator<Row> itr = sheet.iterator();


        while (itr.hasNext()) {
            Row row = itr.next();
            Iterator<Cell> cellIterator = row.cellIterator();
            while (cellIterator.hasNext()) {
                Cell cell = cellIterator.next();
                if (cell.getCellType() == CellType.STRING) {
                    if (cell.getRichStringCellValue().getString().trim()
                            .equals(cellContent)) {

                        cell = cellIterator.next();
                        int val = cell.getColumnIndex();
                        cell = row.getCell(val);
                        String srow = cell.getStringCellValue();
                        return srow;

                    }

                }

            }
        }

        return null;
    }
}
