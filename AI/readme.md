# Aufgabenstellung AI

## Beispiels PDF Datei
<br>

ArtNR  | Bezeichnung  | Frisch  | Teigig  | Kundenbestellung
------------- | ------------- | ------------- | ------------- | -------------
22  | Topfengolatschen  | 5  | 10  | 0
11  | Zimtschnecke  | 2  | 20  | 0
<br>

## Hinweise zur PDF Datei
* Die Spalte **Kundenbestellung** muss **gelöscht/ignoriert** werden, da diese Spalte bei den momentanen Bestellungen nicht benutzt werden und wenn was drinnen steht, dann ist die Information irrelevant
- - - -
* Die Spalte ArtNR besteht aus einer **Ganzzahl** zwischen **60 und 3000**
- - - -
* Die **ArtNR** ist **einzigartig** und ist einem **Produkt immer zugewiesen**
- - - -
* Wie wird ein Produkt bestellt?
    * Produkt kann frisch **und** teigig bestellt werden
    * Produkt kann **nur** frisch bestellt werden
    * Produkt kann **nur** teigig bestellt werden
* Je nach Produkt kann dieses durch eine der davor erwähnten Punkten bestellt werden. 
* Obwohl diese Spalten zum selben Produkt zeigen, sind die Spalten sehr unterschiedlich. Frisch muss nicht mehr gebacken werden und teigig muss gebacken werden. Deswegen sollten diese Spalten nicht vermischt werden

## Ziel der AI
1. Der User kann vorrige Bestellungen als PDF importieren und die Daten werden ausgelesen und gespeichert
2. Wenn im Frontend eine Bestellung abgegeben wird, werden diese Daten gespeichert
3. Bestellungen (sei es teigig **und/oder** frisch) sollen mittels einer AI generiert werden und dem Frontend zurückgegeben werden