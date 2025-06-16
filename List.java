/*
Interfácse: add, remove, contains, size, getValue
Továbbá privátból valszeg: expand, isFull, moveToLeft, lol kellett még indexOfValue is

Meg a firstFreeIndex kb. size()-t jelent,
de a lista maga lehet nagyobb ennél, csak null a többi value
*/

public class List<Type> {
    private Type[] values;
    private int firstFreeIndex;

    public List() {
        this.values = (Type[]) new Object[2];
        this.firstFreeIndex = 0;
    }

    /*
    Lecsekkolja, hogy az indexOfValue egyenlő-e az array lengthjével
    Ha igen, akkor 1.5-el megszorozza az array lenghtjét
    Ezután belehelyezi az arrayen, a firstFreeIndex helyére a valuet
    Majd hozzáad egyet a firstFreeIndex értékéhez
    */
    public void add(Type value) {
        if (isFull()) {
            expandListSize();
        }
        this.values[firstFreeIndex] = value;
        firstFreeIndex++;
    }

    /*
    Bekérünk egy valuet
    If-el lecsekkoljuk, hogy a value indexe kisebb-e mint 0
    Ha igen akkor elhagyjuk a gecibe a metódust
    Ha nem akkor annaka  valuenak az indexétől elindítjuk a moveToLeft metódust
    firstFreeIndex-ből pedig azért kell kivonni egyet, mert ha ott hagynánk a mozdítás
    után, akkor kimaradna egy index a következő addnál
    */
    public void remove(Type value) {
        if (indexOfValue(value) < 0) {
            return;
        }
        moveToLeft(indexOfValue(value));
        this.firstFreeIndex--;
    }

    /*
    Visszadja a bekért indexen lévő értéket
    Ha az index kisebb mint 0 vagy nagyobb/egyenlő mint az első null érték a listán
    dobunk egy hibát
    Ha pedig fasza, visszaadja az indexen lévő valuet
    */
    public Type getValue(int index) {
        if (index < 0 || index >= this.firstFreeIndex) {
            throw new IndexOutOfBoundsException("Index " + index + " outside of bounds.");
        }
        return this.values[index];
    }

    /*
    getfirstFreeIndex, csak hülyén hangzik és hosszú...
    De ugyanazt csinálja mint az ArrayList size() metódusa
    */
    public int size() {
        return this.firstFreeIndex;
    }

    /*
    Lecsekkolja, hogy a value létezik-e a listában. True vagy false
    */
    public boolean contains(Type value) {
        for (int i = 0; i < this.firstFreeIndex; i++) {
            if (this.values[i].equals(value)) {
                return true;
            }
        }
        return false;
    }


    /*
    Innentől minden private, csak a belső működéshez kell.
    Létrehozunk egy temporary listát, ami a values listának a mása
    A values listát újra deklaráljuk, hogy másfélszer akkora legyen az Array
    Ezután egyesével mindent átmásolunk a temporary listáról az új values listára
    */
    private void expandListSize() {
        Type[] tempList = this.values;
        this.values = (Type[]) new Object[this.values.length + this.values.length / 2];
        for (int i = 0; i < tempList.length; i++) {
            this.values[i] = tempList[i];
        }
    }

    /*
    Lecsekkolja, hogy az első szabad index ugyanakkora mint a teljes Array ergo,
    hogy tele van-e a lista. True vagy false értéket ad vissza
    */
    private boolean isFull() {
        if (firstFreeIndex == this.values.length) {
            return true;
        }
        return false;
    }

    /*
    Ez bonyolult...
    Bekér egy "indexet", hogy honnan szeretnénk kezdeni a csúsztatást
    Elindul a for ciklus ettől az indextől és egészen az utolsó értékig tart,
    ami fel van töltve az arrayen és nem null. (Ha csak simán firstFreeIndex-ig kérnénk,
    akkor bekerülne egy null is az Array végéről)
    A for cikluson belül pedig elkezdjök az induló indextől felülírni az értékeket,
    a következő értékre ami a listán van, ezzel gyakorlatilag eltolni az értékeket
    és felülírni azt az egyet amit törölni szeretnénk
    */
    private void moveToLeft(int fromIndex) {
        for (int i = fromIndex; i < this.firstFreeIndex - 1; i++) {
            this.values[i] = this.values[i + 1];
        }
    }

    /*
    Bekérünk egy valuet, majd for ciklussal és if-el csekkoljuk, hogy egyezik-e
    az Arrayen valamelyik value vele
    Ha igen, akkor vissza adjuk annak a valuenak az indexét
    Ha nincs ilyen akkor vissza adunk -1et
    */
    private int indexOfValue(Type value) {
        for (int i = 0; i < this.firstFreeIndex - 1; i++) {
            if (this.values[i].equals(value)) {
                return i;
            }
        }
        return -1;
    }
}
