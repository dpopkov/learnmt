package learn.mt.hlet.m0.e2;

import java.util.Arrays;

public class XOField {

    private final Figure[][] figures;

    public XOField() {
        figures = new Figure[3][3];
    }

    public XOField(XOField xoField) {
        figures = new Figure[3][3];
        for (int i = 0; i < 3; i++) {
            System.arraycopy(xoField.figures[i], 0, figures[i], 0, 3);
        }
    }

    public void setFigure(int x, int y, Figure figure) {
        figures[x][y] = figure;
    }

    public Figure getFigure(int x, int y) {
        return figures[x][y];
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        XOField xoField = (XOField) o;
        return Arrays.deepEquals(figures, xoField.figures);
    }

    @Override
    public int hashCode() {
        return Arrays.deepHashCode(figures);
    }

    @Override
    public String  toString() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                sb.append(figures[i][j] != null ? figures[i][j] : " ");
                sb.append(j != 2 ? "|" : "\n");
            }
            if (i != 2) {
                sb.append("-----\n");
            }
        }
        return sb.toString();
    }
}
