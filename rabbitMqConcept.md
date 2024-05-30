# RabbitMq Json message between Producer and Consumer

## I- How RabbitMQ Works and RabbitMQ Core Concepts

* Producer: Application that sends the messages.
* Consumer: Application that receives the messages.
* Queue: Buffer that stores messages.
* Message: Information that is sent from the producer to a consumer through RabbitMQ.
* Connection: A connection is a TCP connection between your application and the RabbitMQ broker.
* Channel: A channel is a virtual connection inside a connection. When you are publishing or consuming messages from a queue - it's all done over a channel.
* Exchange: Receives messages from producers and pushes them to queues depending on rules defined by the exchange type. To receive messages, a queue needs to be bound to at least one exchange.
* Binding: A binding is a link between a queue and an exchange.
* Routing key: The routing key is a key that the exchange looks at to decide how to route the message to queues. The routing key is like an address for the message.
* AMQP: AMQP (Advanced Message Queuing Protocol) is the protocol used by RabbitMQ for messaging.
* Users: It is possible to connect to RabbitMQ with a given username and password. Every user can be assigned permissions such as rights to read, write and configure privileges within the instance.

**Producers** send/publish the messages to the broker -> **Consumers** receive the messages from the broker. **RabbitMQ** acts as communication middleware between both producers and consumers even if they run on different machines.
While the producer is sending a message to the queue, it will not be sent directly, but sent using the exchange instead. The design below demonstrates how are the main three components are connected to each other.
The exchanges agents that are responsible for routing the messages to different queues. So that the message can be received from the producer to the exchange and then again forwarded to the queue. This is known as the ‘Publishing’ method.

![rabbitmq-workflow.png](src%2Fmain%2Fresources%2Fimages%2Frabbitmq-workflow.png)

The message will be picked up and consumed from the queue; this is called ‘Consuming’.
### Send Message to Multiple Queues
By having a more complex application we would have multiple queues. So the messages will send it in the multiple queues.

![rabbitm-multiple-queues.png](src%2Fmain%2Fresources%2Fimages%2Frabbitm-multiple-queues.png)

Sending messages to multiple queues exchange is connected to the queues by the binding and the routing key. A Binding is a “link” that you set up to connect a queue to an exchange. The Routing key is a message attribute. The exchange might look at this key when deciding how to route the message to queues (depending on exchange type).

### Exchanges
Messages are not published directly to a queue, instead, the producer sends messages to an exchange. An exchange is responsible for the routing of the messages to the different queues. An exchange accepts messages from the producer application and routes them to message queues with the help of bindings and routing keys. A binding is a link between a queue and an exchange.

![exchanges-bidings-routing-keys.png](src%2Fmain%2Fresources%2Fimages%2Fexchanges-bidings-routing-keys.png)

### The message flow in RabbitMQ
* The producer publishes a message to an exchange. When you create the exchange, you have to specify the type of it. The different **types of exchanges** are explained in detail later on.
* The exchange receives the message and is now responsible for the routing of the message. The exchange takes different message attributes into account, such as routing key, depending on the exchange type.
* Bindings have to be created from the exchange to queues. In this case, we see two bindings to two different queues from the exchange. The Exchange routes the message into the queues depending on message attributes.
* The messages stay in the queue until they are handled by a consumer
* The consumer handles the message.
### Types of Exchanges

![exchanges-topic-fanout-direct.png](src%2Fmain%2Fresources%2Fimages%2Fexchanges-topic-fanout-direct.png)

* **Direct**: A direct exchange delivers messages to queues based on a message routing key.
* **Fanout**: A fanout exchange routes messages to all of the queues that are bound to it.
* **Topic**: The topic exchange does a wildcard match between the routing key and the routing pattern specified in the binding.
* **Headers**: Headers exchanges use the message header attributes for routing.

see also: 'https://www.youtube.com/watch?v=IZXJujDy-CU'