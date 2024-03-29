#!/bin/bash
# 上面一行不是注释,是bash bang 表示这份代码使用bash shell的脚本执行,这一行必须写
# 变量定义 注意=号左右不能有空格
NAME="htg"

# 输出变量使用 $变量名 ,或者 ${变量名}
echo "my name is $NAME"
echo ${NAME}


# 单引号和双引号的区别 双引号里面可以使用${变量名}来调用变量而单引号不行
# 反引号和$() 的作用一样，其内部可以直接调用命令
A=`uname -r`
B=$(uname -r)

#declare 的使用
declare KV=$(uname -r)
echo ${KV}  # 输出 5.4.0-91-generic

# $[] 作为四则运算
echo $[1+1]

[ $[ 1 + 1 ] -eq 2 ];echo $?

[[ "$(uname -s)" = 'Linux' && $[ 1 + $? ] -eq 2 ]];echo $?
[ "$(uname -s)" = 'Linux' -a $[1+1] -eq 2 ];echo $?


#系统变量: 系统变量不需要定义 直接调用就OK  $UID $PWD
echo $UID  # 当前用户
echo $PWD  # 当前路径

echo "####################"
# 在输入 sh ./01_bulid.sh 123 456 789 时
echo "\$0 is $0"  # 当前脚本所在的文件
echo "\$$ is $$"  # 当前进程的进程号
echo "\$1 is $1"  # $1表示第一个参数 123
echo "\$2 is $2"  # $2表示第二个参数 456

# $? $# $*
echo "\$? is $?" # $? 上一条命令是否执行成功,成功则返回0 非0表示执行异常或错误
echo "\$# is $#" # $# 表示一共输入了就几个参数 对于 sh ./01_bulid.sh 123 456 789 时 有三个参数 ,返回3
echo "\$* is $*" # $* 表示一共输入的参数的具体值  这里是 123 456 789
echo "\$@ is $@" # $* 表示一共输入的参数的具体值  这里是 123 456 789
