// Driver Requirements
#include <linux/init.h>
#include <linux/module.h>
#include <linux/kernel.h>
#include <linux/fs.h>
#include <linux/uaccess.h>

// Serial Communication requirements
#include <string.h>
#include <stdlib.h>
#include <stdio.h>
#include <unistd.h>
#include <fcntl.h>
#include <termios.h>

#define DEVICE_NAME "glovesy"

static int dev_open(struct inode*, struct file*);
static int dev_release(struct inode*, struct file*);
static ssize_t dev_read(struct file*, char*, size_t, loff_t*);
static ssize_t dev_write(struct file*, const char*, size_t, loff_t*);

static struct file_operations fops = {
    .open = dev_open,
    .read = dev_read,
    .write = dev_write,
    .release = dev_release,
};

static int major;
struct termios tio;
struct termios stdio;
struct termios old_stdio;
int tty_fd;

unsigned char c='D';

static int __init init(void) {
    major = register_chrdev(0, DEVICE_NAME, &fops);

    if (major < 0) {
        printk(KERN_ALERT "Glovesy load failed\n");
        return major;
    }

    // Store file attributes in old_stdio
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

    memset(&tio, 0, sizeof(tio));
    tio.c_iflag = 0;
    tio.c_oflag = 0;
    tio.c_cflag = CS8|CREAD|CLOCAL;
    tio.c_lflag = 0;
    tio.c_cc[VMIN] = 1;
    tio.c_cc[VTIME] = 5;

    tty_fd = open("/dev/ttyACM0", O_RDWR | O_NONBLOCK);
    cfsetspeed(&tio
    printk(KERN_INFO "Glovesy module has been loaded: %d\n", major);
    return 0;
}

static int dev_open(struct inode *inodep, struct file *filep) {
    printk(KERN_INFO "Glovesy Device Opened\n");
    return 0;
}

static ssize_t dev_write(struct file *filep, const char *buffer,
                         size_t len, loff_t *offset) {
    printk(KERN_INFO "Glovesy is Read Only\n");
    return -EFAULT;
}

static int dev_release(struct inode *inodep, struct file *filep) {
    printk(KERN_INFO "Glovesy Device closed\n");
    return 0;
}

static ssize_t dev_read(struct file *filep, char *buffer, size_t len, loff_t *offset) {
    int errors = 0;
    char *message = "Test driver";
    int message_len = strlen(message);

    errors = copy_to_user(buffer, message, message_len);

    return errors == 0 ? message_len : -EFAULT;
}

static void __exit exit(void) {
    unregister_chrdev(major, DEVICE_NAME);
    printk(KERN_INFO "Exiting Kernel Module\n");
}

module_init(init);
module_exit(exit);
