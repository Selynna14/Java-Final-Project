����   @ V
      java/lang/Object <init> ()V  java/awt/Rectangle
  	      Obj objRect Ljava/awt/Rectangle;	     collison Z	     worldCol I	     worldRow	     	ObjWorldX	    ! "  	GamePanel worldX	  $ % & p LPlayer;	 ( ) * +  Player screenX	  - .  	ObjWorldY	  0 1  worldY	 ( 3 4  screenY
 6 7 8 9 : java/util/Objects requireNonNull &(Ljava/lang/Object;)Ljava/lang/Object;@P      
  > ? @ setRect (DDDD)V	  B C D image Ljava/awt/image/BufferedImage;
 F G H I J java/awt/Graphics2D 	drawImage 5(Ljava/awt/Image;IIIILjava/awt/image/ImageObserver;)Z
 F L M N draw (Ljava/awt/Shape;)V name Ljava/lang/String; Code LineNumberTable #(Ljava/awt/Graphics2D;LGamePanel;)V 
SourceFile Obj.java !       C D    O P                    .                     Q   G     *� *� Y� 	� 
*� *� *� �    R                 M S  Q   � 	    e*� ,� ,� #� '`d>*� ,,� /,� #� 2`d6*� 
��,� 5W ;,� 5W ;� =+*� A,� 5W@,� 5W@� EW+*� 
� K�    R          % ! A " \ # d $  T    U