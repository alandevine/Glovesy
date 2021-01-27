#include <string.h>
#include <stdlib.h>
#include <stdio.h>
#include <unistd.h>
#include <fcntl.h>
#include <termios.h>

int main(int argc, char** argv) {
    struct termios tio;
    struct termios stdio;
    struct termios old_stdio;
    int tty_fd;
    
    unsigned char c='D';
    tcgetattr(STDOUT_FILENO, &old_stdio);

    memset(&stdio, 0, sizeof(stdio));
    stdio.c_iflag = 0;
    stdio.c_oflag = 0;
    stdio.c_cflag = 0;
    stdio.c_lflag = 0;
    stdio.c_cc[VMIN] = 1;
    stdio.c_cc[VTIME] = 0;
    tcsetattr(STDOUT_FILENO, TCSANOW, &stdio);
    tcsetattr(STDOUT_FILENO, TCSAFLUSH, &stdio);
    fcntl(STDIN_FILENO, F_SETFL, O_NONBLOCK); // make reads non blocking

    memset(&tio, 0, sizeof(tio));
    tio.c_iflag = 0;
    tio.c_oflag = 0;
    tio.c_cflag = CS8|CREAD|CLOCAL; // 8n1, See termios.h for more info
    tio.c_lflag = 0;
    tio.c_cc[VMIN] = 1;
    tio.c_cc[VTIME] = 5;

    tty_fd = open("/dev/ttyACM0", O_RDWR | O_NONBLOCK);
    cfsetospeed(&tio, B115200);
    cfsetispeed(&tio, B115200);

    tcsetattr(tty_fd, TCSANOW,&tio);
    while (c != 'q') {
        if(read(tty_fd, &c, 1)>0)
            write(STDOUT_FILENO,&c,1);
    }

    close(tty_fd);
    tcsetattr(STDOUT_FILENO, TCSANOW, &old_stdio);

    return EXIT_SUCCESS;
}
