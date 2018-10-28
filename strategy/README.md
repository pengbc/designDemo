书中以一个列子来展开策略模式，方便理解。
1：模拟鸭子游戏：游戏中会出现各种鸭子，一边游泳戏水，一边呱呱叫，设计一个鸭子超类，并让各种鸭子继承此超类。
所有的鸭子都会呱呱叫（Quack）也会游泳（Swim）所以由超类负责处理这部分的实现代码。每一种鸭子的外观都不同，所以display()方法是抽象的。

class abstract Duck{

    public void quack(){};
    public void swim(){};
    public abstract void display();
}
class MallardDuck extends Duck{
    @Override
    public void display(){
    //外观是绿头
    }
}
class RedheadDuck extends Duck{
    @Override
    public void display(){
    //外观是红头
    }
}
主管之后希望鸭子能飞。
设想：只需要在Duck类中加上fly()方法，然后让所有鸭子都会继承fly().

class abstract Duck{
    public void quack(){};
    public void swim(){};
    public void fly(){};
    public abstract void display();
}

但是，可怕的问题发生了：发现很多的“橡皮鸭子”在天上飞。所以并非Duck所有的子类都会飞。像橡皮鸭子这样没有生命的鸭子。
当涉及“维护”时，为了“复用”目的而使用继承，结局并不完美
这时候想到继承。可以把橡皮鸭类中的fly()方法覆盖掉。

class RubberDuck extends Duck{
    @Override
    public void quack(){
    //吱吱叫
    }
    @Override
    public void display(){
    //橡皮鸭
    }  
    @Override
    public void fly(){
    //覆盖，变成什么事都不做
    }
}

这里有个问题：利用继承来提供Duck的行为，这会导致下列哪些缺点？(多选)
A.代码在多个子类中重复。
B.运行时的行为不容易改变。
C我们不能让鸭子跳舞。
D很难知道所有鸭子的全部行为。
E鸭子不能同时又飞又叫。
F改变会牵一发动全身，造成其他鸭子不想要的改变。
认识到继承可能不是答案，每当有新的鸭子子类出现，他就要被迫检查并可能需要覆盖fly()和quark()……所以我们需要一个更清晰的方法，让“某些”鸭子类型可飞或可叫。
我们可以把fly()从超类中取出来，放进一个“Flyable接口”中，这么一来，只有会飞的鸭子才实现此接口，同样的方式，也可以设计一个“Quackable接口”。

interface Flyable{
    public void fly();
}
interface Quackable{
    public void quack();
}
class abstract Duck{
    public void swim(){};
    public abstract void display();
}
class MallardDuck extends Duck implements Flyable,Quackable{
    @Override
    public void display() {        
    }
    @Override
    public void fly() {
    }
    @Override
    public void quack(){
    }
}
你会发现虽然Flyable与Quackable可以解决“一部分”问题，但是却造成代码无法复用，这只能算是从一个噩梦跳进另一个噩梦。甚至，在会飞的鸭子中，飞行的动作可能还用多种变化。

这里有个问题：驱动改变的因素很多。找出你的应用中需要改变代码的原因以一列出来。
1：我们的顾客或用户需要别的东西，或者想要的新功能。
2：我的公司决定采用别的数据库产品，又从另一家厂商买了数据，这造成数据格式不兼容。唉！
3：
……
把问题归零……
现在我们知道使用继承并不能很好地解决问题，因为鸭子的行为在子类里不断地改变，并且让所有的子类都有这些行为是不恰当的，Flyable与Quackable接口一开始似乎还挺不错，但是java接口不具有实现代码，所以继承接口无法达到代码的复用。这就意味着：无论何时你需要修改某个行为，你必须得往下追踪并在每一个定义此行为的类中修改它，一不小心，可能会造成新的错误。
幸运的是，有一个设计原则，恰好适用于此状况：
第一个设计原则：
找出应用中可能需要变化之处，把它们独立起来，不要和那些不需要变化
的代码混在一起。
把会变化的部分取出并封装起来，以便以后可以轻易地改动或扩充此部分，而不影响不需要变化的其他部分了。
分开变化和不会变化的部分
我们准备建立两组类（完全远离Duck类），一个是“fly”相关的，一个是“quack”相关的，每一组类将实现各自的动作。分别实现“呱呱叫”，“吱吱叫”，“安静（不叫）”
设置鸭子的行为
我们希望一切能有弹性，我们还想能够“指定”行为到鸭子的实例。比方说，我们想要产生一个新的绿头鸭实例，并指定特定“类型”的飞行行为给它，干脆顺便让鸭子的行为可以动态的改变，我们应该在鸭子类中包含设定行为的方法， 这样可以在“运行时”动态地“改变”绿头鸭的飞行行为
第二个设计原则：
针对接口编程，而不是针对实现编程

interface  FlyBehavior{
    void fly();
}

class FlyWithWings implements  FlyBehavior{

    @Override
    public void fly() {
        //实现鸭子的飞行动作
    }
}
class FlyNoWay implements FlyBehavior{

    @Override
    public void fly() {
        //什么都不做，不会飞
    }
}
利用接口代表每个行为，FlyBehavior与QuackBehavior，而行为的每个实现都将实现其中的一个接口。
所以这次鸭子类不会负责实现Flying与Quacking接口，反而是由我们制造一组其他类专门实现FlyBehavior与QuackBehavior，这就称为“行为”类。由行为类而不是Duck类来实现行为接口。
我们的新设计中，鸭子的子类将使用接口（FlyBehavior与QuackBehavior）所表示的行为，所以实际的“实现”不会被绑死在鸭子的子类中。
这里有一个问题：为什么非要把FlyBehavior设计成接口，为何不适用抽象
超类，这样不就可以使用多态了吗？
“针对接口编程”真正的意思是“针对超类型编程”
这里所谓的“接口”有多个含义，接口是一个“概念”，也是一种java的interface构造。“针对接口编程”，关键就在多态，利用多态，程序可以针对超类型编程，执行时会根据实际状况执行到真正的行为，不会被绑死在超类型的行为上。
实现鸭子的行为

interface QuackBehavior{
    void quack();
}

class Quack implements QuackBehavior{

    @Override
    public void quack() {
        //实现鸭子呱呱叫
    }
}
class Squeak implements QuackBehavior{

    @Override
    public void quack() {
        //橡皮鸭吱吱叫
    }
}
class MuteQuack implements QuackBehavior{

    @Override
    public void quack() {
        //什么都不做，不会叫
    }
}
这样的设计，可以让飞行和呱呱叫的动作被其他的对象复用，因为这些行为已经与鸭子类无关了。
而我们可以新增一些行为，不会影响到既有的行为类，也不会影响“使用”到飞行行为的鸭子类。
整合鸭子的行为
鸭子现在会将飞行和呱呱叫的动作“委托”别人处理，而不是使用定义在Duck类（或子类）内的呱呱叫和飞行方法。
首先在Duck类中“加入两个实例变量”，分别为“flyBehavior”，“quackBehavior”,
声明为借口类型（而不是具体类实现类型），每个鸭子对象都会动态地设置这些变量以在运行时应用正确的行为类型。
也必须将Duck类与其所有子类中的fly()与quack()删除，因为这些行为已经被搬到连个行为类中了。

abstract class  Duck{
    FlyBehavior flyBehavior;
    QuackBehavior quackBehavior;

    public void performQuack(){
        quackBehavior.quack();
    }
    public void performFly(){
        flyBehavior.fly();
    }
    public abstract void display();
    public void swim(){        
    }
}
更多的整合
如何设定flyBehavior与quackBehavior的实例变量。
如MallardDuck类：

class MallardDuck extends Duck{

    public MallardDuck(){
        quackBehavior=new Quack();
        flyBehavior=new FlyWithWings();
    }

    @Override
    public void display() {
        System.out.println("I'm a real Mallard duck");
    }
}
前面我们说过将不对具体实现编程，但是我们在构造器中却实例化了具体的Quack和FlyWithWings类。在本书的后续内容中，我们会有更多的模式可用，就可以修正这一点了。
动态设定行为
在Duck类中，加入两个新方法：

 public void setFlyBehavior(FlyBehavior fb){
        flyBehavior=fb;
    }
  public void setQuackBehavior(QuackBehavior qb){
        quackBehavior=qb;
    }
我们不再把鸭子的行为说成“一组行为”，我们开始把行为想成是“一族算法”。算法代表鸭子能做的是（不同的叫法和飞行法），
“有一个”可能比“是一个”更好
“有一个”：每一个鸭子都有一个FlyBehavior和一个QuackBehavior，好将飞行和呱呱叫委托给它们代为处理。
当你将两个类结合起来使用，如同本例一般，这就是组合，这种做法和“继承”不同的地方在于，鸭子的行为不是继承来的，而是和适当的行为对象“组合”来的
第三个设计原则：
多用组合，少用继承
第一个模式：
策略模式：定义了算法族。分别封装起来，让他们之间可以互相替换，
此模式让算法的变化独立于使用算法的客户
