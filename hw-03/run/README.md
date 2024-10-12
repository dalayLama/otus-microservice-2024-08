## Пояснительная записка

### Запуск приложения:

В директории [kube-conf](../kube-conf) расположены 3 манифеста:
- [Deployment.yaml](../kube-conf/Deployment.yaml)
- [Ingress.yaml](../kube-conf/Ingress.yaml)
- [Service.yaml](../kube-conf/Service.yaml)

Для запуска приложения, необходимо, находясь в этой папке выполнить команду:

"_**kubectl apply -f .**_"

Для остановки приложения:

"_**kubectl delete -f .**_"

### Тестирование

Для тестирования есть [коллекция из postman](Otus.postman_collection.json), которая содержит 4 request-а:
- /health
- /otusapp/artur/health
- /otusapp/anyname/
- /otusapp/anyname/test/test

Все url-ы возвращают ответ **{"status": "OK"}**, url-ы помимо "/health" были сделаны для задания со свездойчкой, согласно шаблону "/otusapp/{name}/(*)"

Для успешного запуска данной коллекции чере newman необходимо выполнить команду(в host должен быть прописан arch.homework):

"_**newman run Otus.postman_collection.json --folder "hw-03"**_"


