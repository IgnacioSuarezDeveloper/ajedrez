// Refactored drag and drop code

class PiezaBase {
    // ... existing class content ...

    public void moverPieza(int x, int y) {
        // logic to move the piece based on the game rules
        if (esMovimientoValido(x, y)) {
            // perform drag and drop logic
        }
    }

    private boolean esMovimientoValido(int x, int y) {
        return obtenerCasillaDelRaton(x, y) != null
                && esValorEnRango(x, y)
                && (casillaSinPieza(x, y) || casillaConEnemigoEnemigo(x, y));
    }

    private Casilla obtenerCasillaDelRaton(int x, int y) {
        // helper method to get the cell based on mouse position
        // ... implementation ...
    }

    private boolean esValorEnRango(int valor) {
        // check if value is in a valid range
        return valor >= 0 && valor < tablero.length;
    }

    private boolean casillaSinPieza(int x, int y) {
        // check if the cell is empty
        return tablero[x][y] == null;
    }

    private boolean casillaConEnemigoEnemigo(int x, int y) {
        // check if there is an enemy piece in the cell
        return tablero[x][y] != null && tablero[x][y].esEnemigo(this);
    }

    // ... other methods ...
}