# Resilience Dojo

Welcome to this Dojo where you will learn to master 3 different patterns for protecting your application from failure and making your application more resilient (and to bake some :cake:):

* Circuit Breaker
* Time Limiter
* Retry

We will utilize the library [resilience4j](https://github.com/resilience4j/resilience4j)

## How to master the dojo

There are 5 dojo steps. Each step will teach you a valuable lesson. Each step consists of a an implementation and a test.

Read the implementation instructions in the comments in each file or further below in this file.

When you feel that you have implemented it correctly then run the test associated with the implementation.

The first implementation is `Bakery_1` and the first test is `Bakery_1_YourFirstBakeryTest`

# Dojo Steps

# 1. Your first bakery

We need to verify that you can bake some cake!

**Mission:** Bake a cake

# 2. Your second bakery with a bad baker

You have hired a new baker. He will burn every other cake.

**Mission:** Construct a Circuit Breaker that should trip/open if your baker has failed to bake 20 cakes..


# 3. Your third bakery with a sleepy mixer

You have hired a new mixer. He will fall asleep every other mix.

**Mission:** Construct a Time Limiter that should interrupt your mixer if he has failed to mix the ingredients in a reasonable time (100-500 milliseconds).

# 4. Your fourth bakery where your baker... is still bad

Instead of burning every other cake, your baker will now always burn the first cake he tries to bake but will always succeed with his second cake.

**Mission:** Construct a Retry that should retry the baking if it fails.

# 5. Your final bakery with your lovable staff

Your mixer falls asleep every other mix. Your baker burns every other cake.

**Mission:** Construct a Time Limiter AND a Circuit Breaker that works in conjunction. It should trip/open if your mixer and baker has failed to mix/bake 20 cakes in total.