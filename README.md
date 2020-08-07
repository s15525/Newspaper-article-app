# PRM3
Zadanie polega na stworzeniu aplikacji RSS do przeglądania najnowszych wiadomości. Aplikacja powinna zapisywać stan odczytania artykułu i 
wyświetlać artykuł inaczej niż artykuł nieprzeczytany. Użytkownik powinien mieć możliwość oznaczenia artykułu jako ulubiony i wyświetlenia go na osobnej 
liście artykułów ulubionych. Zapis powinien być dokonany wzewnętrznej bazie danych (np.: firebase, lub własny backend). Aplikacja powinna posiadać:</br>
<h3>Ekran logowania/autentykacji:</h3>Logowanie po adresie e-mail i haśle lub inny sposób z wykorzystaniem dostawcy tożsamości; ang. identity provider, np: logowanie przez facebooka. 
Dodatkowo wprzypadku, kiedy użytkownik nie ma konta, powinien mieć możliwość założenia go.</br>
<h3>Ekran listy artykułów:</h3>Zestawienie reprezentujące listę najnowszych artykułów pochodzących z dowolnego kanału RSS. 
Dane należy parsować bezpośrednio z kanału RSS bez udziału konwerterów online (takich jak na przykład ​https://rss2json.com/​).
Każdy element w liście artykułów powinien posiadać następujące informacje: tytuł wiadomości, obrazek (jeśli dany artykuł takowy posiada,
należy wybrać taki RSS,który zawiera obrazki), krótki opis. Po wybraniu elementu, użytkownikowi powinien wyświetlić się widok na cały artykuł, 
a wpis powinien zostać oznaczony jako przeczytany (co oznacza, że wyświetli się ponownie użytkownikowi na liścieartykułów jako artykuł przeczytany, 
np.: wyszarzony).Użytkownik powinien też mieć możliwość oznaczenia artykułu jako ulubiony co będzie powodowało jego wyświetlenie w osobnej zakładce.</br>
<h3>Ekran ulubionych artykułów:</h3>Zestawienie reprezentujące listę ulubionych 
artykułów użytkownika (każdy zalogowany użytkownik powinien mieć własną listę). Wygląd elementów powinienbyć zbliżony do widoku elementów z poprzedniego ekranu
