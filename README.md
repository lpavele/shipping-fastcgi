# shipping-fastcgi

Доставка из ресторанов

Клиент может заказать из тех ресторанов, а) которые находятся недалеко от клиента и б) возле которых есть поблизости курьеры. 

Выбран REST, т.к. для этого достаточно основных методов HTTP, чтобы описать все операции, производимые над ресурсами, а также получается более интуитивный.



----------Сущности----------

Restaraunt
	id:number
	name:string
	description:string
	latitude:any
	longitude:any
	isWorking:boolean //работает ли сейчас

Courier
	id:number
	fio:string
	isWorking:boolean //работает ли сейчас
	isFree:boolean //свободен ли курьер
	currentLatitude:any
	currentLongitude:any

Order
	id:number
	restaraunt:Restaraunt
	courier:Courier
	startDate:long
	finishDate:long
	status:enum //состояние заказа



----------API----------

GET	---/restaraunts? [latitude=<широта> & longitude=<долгота>] // список еще работающих ресторанов, которые недалеко от этих координат (например, меньше 2км)
{
   _links:{
	self:'/restaraunts?latitude=...&longitude=...'
   },
   data:[
      {
	_links:{
	   self:'/restaraunts/1',
	   search-couriers:'/restaraunts/1/couriers'
	},
	id:1,
   	name:'GoodFood',
   	description:'It's very good food.',
   	latitude:77.037684,
   	longitude:38.898748,
	isWorking:true,
      }
      ...
   ]
}
GET	---/restaraunts/:id
{
	_links:{
	   self:'/restaraunts/1'
	},
	id:1,
   	name:'GoodFood',
   	description:'It's very good food.',
   	latitude:77.037684,
   	longitude:38.898748,
	isWorking:true,
}
GET	---/restaraunts/:id/couriers //список свободных курьеров, которые рядом с этим рестораном
{
   _links:{
	self:'/restaraunts/1/couriers',
	restaraunt:'/restaraunts/1'
   },
   data:[
      {
	_links:{
	   self:'/couriers/1',
	   search-couriers:'/restaraunts/1/couriers',
	   do-order:'/users/.../orders'
	},
	id:100,
	fio:'Petrov Petya',
	isWorking:true,
	isFree:true,
	currentLatitude:77.037680,
	currentLongitude:38.898750
      }
      ... 
   ]
}
POST	---/restaraunts //добавить ресторан
PUT	---/restaraunts/:id //обновить информацию о ресторане
//////DELETE	---/restaraunts/:id //удалить ресторан

GET	---/couriers //получить список курьеров
{
   _links:{
	self:'/couriers"
   },
   data:[
      {
	_links:{
	   self:'/couriers/100',
	},
	id:100,
	fio:'Petrov Petya',
	isWorking:true,
	isFree:true,
	currentLatitude:77.037680,
	currentLongitude:38.898750
      }
      ...
   ]
}
GET	---/couriers/:id //курьер с id=:id
{
	_links:{
	   self:'/couriers/100',
	   orders:'/couries/100/orders'
	},
	id:1,
   	name:'GoodFood',
   	description:'It's very good food.',
   	latitude:77.037684,
   	longitude:38.898748,
	isWorking:true,
}
GET	---/couriers/:id/orders //получить список заказов курьера с id=:id
{
   _links:{
	self:'/couriers/100/orders",
	courier:'/couries/100'
   },
   data:[
      {
	_links:{
	   self:'/orders/2000',
	   restaraunt:'/restaraunts/1',
	   finish-order:'/couries/100/orders/2000/task'
	},
	id:2000,
	startDate:145754575,
	finishDate:null,
	status:'processing'
      },
	_links:{
	   self:'/orders/2001',
	   restaraunt:'/restaraunts/1',
	   accept-order:'/couries/100/orders/2001/task'
	},
	id:2001,
	startDate:145754575,
	finishDate:null,
	status:'in-waiting'
      },
      ...
   ]
}
POST	---/couriers/:courierId/orders/:orderId/task //принять или  завершить заказ
POST	---/couriers //добавить курьера
PUT	---/couriers/:id //обновить информацию о курьере
//////DELETE	---/couriers/:id //удалить курьера

GET	---/orders
GET	---/orders/:id
//////POST	---/orders
//////PUT	---/orders/:id
//////DELETE	---/orders/:id

GET	---/users/:id/orders //заказы клиента
{
   _links:{
	self:'/users/.../orders"
   },
   data:[
      {
	_links:{
	   self:'/orders/2000',
	   restaraunt:'/restaraunts/1',
	   courier:'/couries/100'
	},
	id:2000,
	startDate:145754575,
	finishDate:null,
	status:'processing'
      },
	_links:{
	   self:'/orders/2002',
	   restaraunt:'/restaraunts/1',
	   courier:'/couries/101'
	},
	id:2002,
	startDate:145754575,
	finishDate:null,
	status:'processing'
      },
      ...
   ]
}
POST	---/users/:id/orders //добавить заказ
	
