# CS583_DMTM

MS_GSP   --> Minimum support Generate Sequence Patterns Algorithm.
  As the name suggests the algorithm generates sequence patterns. It is an extension of the Apriori Algorithm. Apriori was originally used for mining patterns of words or transactions. However the order of the words was ignored in the mining process. By introducing the order in the algorithm, Apriori is extended to form GSP. To add more value to the algorithm, minimum support was introduced to handle those less frequently occuring words in the data set. SDC ( support difference constraint ) is also used to avoid of less frequent items and more frequent items from occuring together. 
  Project was done considering the sequence of transactions of each customer are given with the product Id and other parameters such as MIS and SDC. The project generates sequences of length starting from 1 to maximum possible length which satisfies the support and confidence given as parameters. 
  
