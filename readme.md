Se va implementa un joc de supraviețuire (survival game).
Jucătorul se poate deplasa pe o hartă folosind tastele W A S D.

Această hartă de joc este bazată pe o matrice pătrată, unde fiecare locație din matrice poate fi:
- goală (definită ca 0)
- poate conține un obiect
- poate conține un inamic

Reprezentarea grafica pentru obiecte, inamici, locatii libere este la alegere.

Dacă jucătorul întâlnește un loc gol, poate crea un obiect în acea locație.
Dacă locația conține un obiect, jucătorul poate ridica obiectul, eliberând astfel locația.
Dacă jucătorul întâlnește un inamic, începe o luptă: jucătorul lovește primul, apoi inamicul, și tot așa până când unul dintre ei moare.

Obiectele pot fi:
- arbori
- roci
- cereale

Acestea obiecte lasă următoarele resurse:
- arborii -> o anumită cantitate de lemn
- rocile -> o anumită cantitate de piatră
- cerealele -> o anumită cantitate de hrană

Cu resursele obținute (lemn, piatră, hrană), se pot crea obiecte sau clădiri. Pentru a crea un obiect sau o clădire este nevoie de o locație liberă pe matrice. Dacă se construieste o clădire, acea locație va fi ocupată.


Detalii de implementare

Se va crea o clasă abstractă pentru a generaliza obiectele care pot fi colectate (Grain, Tree, Rock).

Atribute:
- quantity și quality (cantitatea de lemn pe care un arbore o lasă când este tăiat; calitatea influențează cantitatea cu un multiplicator).

Se va utiliza o enumerare pentru atributul quality (valori: Common, Rare, Epic) in cazul obiectelor.

Metode abstracte:
- gatherable()
- toString()

Se va implementa această clasă și fiecare dintre cele 3 subclase de obiecte colectabile.

Fiecare dintre aceste clase va avea:
- metode getters și setters
- metoda toString()

A doua clasă abstractă este Character, care va fi moștenită de clasele Player și Enemy.

Atribute:
- name, attack, status (valori posibile: alive sau dead), defense, health, și un inventory (set de obiecte).

Metode abstracte:
- damage()
- takeDamage()
- die()

Clasa Player va avea atribute suplimentare legate de gestionarea resurselor:
- wood, stone, food
  si metode pentru a colecta resursele, a gestiona interacțiunile și crafting-ul.

Un inamic (Clasa Enemy), odată învins, poate să lase în urmă un obiect (de ex. sword, armor, helmet, etc.). Se vor implementa metode și atribute suplimentare pentru a gestiona acest comportament.


Se va implementa o altă clasă pentru obiecte.

Atribute:
- name și alte atribute care pot modifica jucătorul. Acestea pot crește sănătatea (health), atacul (attack) sau apărarea (defense) jucătorului.

Obiectele pot fi create (crafted) sau lăsate de inamici (dropped).

Se va implementa un comparator care să permită sortarea obiectelor (cele din inventory-ul jucătorului) în funcție de diverse criterii.

Se va implementa o clasă pentru clădiri care pot fi construite, fiecare având o funcție specială. Exemple:
- dacă jucătorul se află într-un loc cu o clădire "Fântâna vieții" (Fountain of Life), își va recupera sănătatea la maxim.
- dacă jucătorul construiește un "Monument al sabiei" (Sword Monument), va primi un boost permanent de atac de 10%.
  Se pot imagina și alte efecte.

Se vor implementa metode care să modifice statisticile jucătorilor fie la crearea, fie la interacțiunea cu aceste clădiri.

Clădirile și obiectele fabricate vor necesita lemn și piatră pentru a fi create.

Se vor utiliza fișiere externe (pentru citire și scriere) pentru diverse scenarii:
incarcare harta
incarcare listă de obiecte predefinite și statisticile lor.
incarcare listă de inamici
salvare / restaurare stare joc într-un fișier

Se va crea o functionalitate pentru a edita harti (pentru a defini obiectele, inamicii si locatiile libere de pe harta) si a salva hartile in fisiere.

Se pot adăuga atribute/metode suplimentare în clasele indicate si se pot crea clase noi dacă este necesar.

Se va testa implementarea si se va permite  utilizatorului să se deplaseze pe matrice folosind tastele. Execuția jocului trebuie să fie interactivă.

Se va utiliza pentru implementare Graphics2D sau OpenGL.