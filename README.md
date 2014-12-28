#Caesar Cipher Cracker

Caesar Cipher Cracker is a small java program for decrypting a message that
has been encrypted with with a [Caesar Cipher]
(http://en.wikipedia.org/wiki/Caesar_cipher).
This project was written along with my own implementation of a [Caesar cipher]
(https://github.com/jbatch/caesercipher)
also in java.

## How it works
As it turns out an encryption algorithm as simple as a shift cipher offers no
real communication security. Any person could crack an encoded message by hand
by simply shifting the encrypted message themselves by one each time until
they end up with the resemblance of a message. The user wouldn't have to test 
more than 25 different shift values before they were guaranteed to get the
original message.

As it turns out there is also another vulnerability in the Caesar cipher
method. In English not all letters appear in words with an equal likelihood.
Some letters are in fact a lot more common than others (like the letter 'e'
making up 12% of English words.)

Using this information the program takes a message, tests all possible
shift values and looks at which one has a letter distribution closest to
English. A pretty cool idea I thought.

## Usage
To use this you must first clone the repo

`git clone https://github.com/jbatch/caeserciphercracker.git`

`cd caesarciphercracker`

compile the code

`javac *.java`

Make sure you have the message you want to decrypt stored in a plain text file
in the same directory. Now to run the program

`java CaesarCipherCracker filename.txt` where filename.txt is the name of your
message file

The program will then attempt to work out what shift value was used to
encrypt your message and give you the most likely decrypted message.

## Drawbacks
Obviously this program is not going to work on 100% of all messages you give
it. For example if you give it a string like "message" it will note that 's'
is the most common character and probably try to shift that onto 'e' to
minimize the average difference in proportions. Through testing I've found it 
has always worked on messages at least ten words long. The longer a message
is the closer it will get to matching the average for English. So this is
really meant for decrypting larger than a word or two messages.

The other drawback is that it is hard coded to find messages encrypted with a
Caesar cipher with a consistent shift value and assumes the original message
was in English (a fair assumption I think.) Obviously it will not likely be as
effective in decrypting French messages. But maybe it will. There's a 1 in 26
chance of it fluking the right shift value anyway.