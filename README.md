# SearchToFile
Написать программу для поиска заданного текста в лог файлах.
Пользователь должен иметь возможность указать папку в сети или на жестком диске, в которой будет происходить поиск заданного текста включая все вложенные папки.
Должна быть возможность ввода текста поиска и ввода типа расширения файлов, в которых будет осуществляться поиск (расширение по умолчанию *.log).
Результаты поиска можно вывести в левой части приложения в виде дерева файловой системы только те файлы, в которых был обнаружен заданный текст.
В правой части приложения выводить содержимое файла с возможностью навигации по найденному тексту (выделить все, вперед/назад).

# Главные задачи по улучшению приложения:
- Выделение найденных слов в тексте.
- Стабильное открытие файлов более 1 гб.
- Очистка памяти от контента на удаленных вкладках.

# Инструкция по запуску 
1. Скачать файл в папке репозитория libs\\*.
2. Запустить скаченный файл.

# Инструкция по установке (нужен инструмент сборки Gradle):
1. Скачать весь проект.
2. В командной строке в папке проекта использовать команду "gradle build".
3. Запустить jar файл в папке build\libs\\*.
