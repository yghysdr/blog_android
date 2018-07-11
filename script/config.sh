#!/bin/bash
module_name=""
project_name=""
root_package_name=""
package_name=""
package_path=""
projectType=""
finish="n"
confirm="n"

cd ../modules

getProjectType(){
    echo -e "================================================"
    echo -e " 1 :  Module"
    echo -e " 2 :  Mediator"
    echo -e " 3 :  Module && Mediator"
    echo -e "================================================"
    read -p "Enter Project Type Number: " projectTypeNumber

    case $projectTypeNumber in  
        "1"|"Module")  
            projectType="1"
            ;;
        "2"|"Mediator")
            projectType="2"
            ;;
        "3"|"Module && Mediator")
            projectType="3"
            ;;
    esac
   

    if [[ -z ${projectType} ]]; then
        echo "Invalid type number." && exit 1
    fi

    if test -z "$projectType"; then
        getProjectType
    fi
}

getProjectName(){
    echo -e "================================================"
    echo -e "please enter module name:"
    echo -e "example: if enter login, will create module"
    case $projectType in
        "1")
            echo -e "module_login"
            ;;
        "2")
            echo -e "mediator_login"
            ;;
        "3")
            echo -e "module_login"
            echo -e "mediator_login"
            ;;
    esac
    echo -e "================================================"
    read -p "Enter module name: " module_name

    case $projectType in
        "1")
            if [ -d  "module_${module_name}" ]; then
                echo "$module_{module_name} already exist." && exit 1
            fi
            ;;
        "2")
            if [ -d "mediator_${module_name}" ]; then
                echo "mediator_${module_name} already exist." && exit 1
            fi
            ;;
        "3")
            if [ -d "module_${module_name}" ]; then
                echo "module_${module_name} already exist." && exit 1
            fi
            if [ -d "mediator_${module_name}" ]; then
                echo "mediator_${module_name} already exist." && exit 1
            fi
            ;;
    esac

    if [[ -z ${module_name} ]]; then
        echo "Invalid module_name." && exit 1
    fi

    if test -z "${module_name}"; then
        getProject
    fi
}

getPackageName(){
    echo -e "================================================"
    echo -e "please enter root package name:"
    echo -e "example: if enter io.example, will create package"
    case $projectType in
        "1")
            echo -e "io.example."${module_name}
            ;;
        "2")
            echo -e "io.example.mediator."${module_name}
            ;;
        "3")
            echo -e "io.example."${module_name}
            echo -e "io.example.mediator."${module_name}
            ;;
    esac
    echo -e "================================================"
    read -p "Enter Package Name:" root_package_name

    if [[ -z ${root_package_name} ]]; then
        echo "Invalid project name." && exit 1
    fi

    if test -z "${root_package_name}"; then
        getPackageName
    fi
}

getInfomation(){
    getProjectType
    getProjectName
    getPackageName

    echo -e "=========module_info========="
    case $projectType in
        "1")
            echo -e "module name: moudle_"${module_name}
            echo -e "package name:"${root_package_name}"."${module_name}
            echo -e "package path:"${root_package_name//./\/}"/"${module_name}
            ;;

        "2")
            echo -e "module name: mediator_"${module_name}
            echo -e "package name: "${root_package_name}".mediator."${module_name}
            echo -e "package path: "${root_package_name//./\/}"/mediator/"${module_name}
            ;;
        "3")
            echo -e "module name: moudle_"${module_name}
            echo -e "package name:"${root_package_name}"."${module_name}
            echo -e "package path:"${root_package_name//./\/}"/"${module_name}
            echo -e "module name: mediator_"${module_name}
            echo -e "package name: "${root_package_name}".mediator."${module_name}
            echo -e "package path: "${root_package_name//./\/}"/mediator/"${module_name}
            ;;
    esac
    echo -e "============================"
    read -p "confirm info && create module? (y/n):" confirm
    if [ "$confirm" == "y" -o "$confirm" == "Y" ]; then
        create
    else
        getInfomation
    fi
}

# 创建项目
create_project(){
    echo -e "create  ${project_name}"

    echo -e "create  ${project_name}/src/main/java/${package_path}"
    mkdir -p         ${project_name}/src/main/java/${package_path}

    echo -e "create  ${project_name}/src/main/res/drawable"
    mkdir -p         ${project_name}/src/main/res/drawable

    echo -e "create  ${project_name}/src/main/res/values"
    mkdir -p         ${project_name}/src/main/res/values

    echo -e                                "create  ${project_name}/.gitignore"
    cp ../script/template/.gitignore                ${project_name}/

    echo -e                                "create  ${project_name}/.proguard-rules.pro"
    cp ../script/template/proguard-rules.pro        ${project_name}/

    echo -e                                 "create  ${project_name}/src/main/res/values/strings.xml"
    cp ../script/template/strings.xml                ${project_name}/src/main/res/values/
    sed -i "" s/project_name/${project_name}/g       ${project_name}/src/main/res/values/strings.xml

    echo -e "update  settings.gradle"
    sed -i "" '$a\
    ',\':${project_name}\'   settings.gradle
}

# 创建额外module需要的额外东西
create_module_ext(){
    echo -e                                 "create  ${project_name}/src/main/AndroidManifest.xml"
    cp ../script/template/AndroidManifest.xml        ${project_name}/src/main/AndroidManifest.xml
    sed -i "" s/package_name/${package_name}/g       ${project_name}/src/main/AndroidManifest.xml

    echo -e "create  ${project_name}/src/main/module/AndroidManifest.xml"
    mkdir -p         ${project_name}/src/main/module
    cp ../script/template/module_AndroidManifest.xml           ${project_name}/src/main/module/AndroidManifest.xml
    sed -i "" s/package_name/${package_name}/g                 ${project_name}/src/main/module/AndroidManifest.xml

    echo -e                                    "create  ${project_name}/build.gradle"
    cp ../script/template/module_build.gradle           ${project_name}/build.gradle
    sed -i "" s/project_placeholder/${project_name}/g   ${project_name}/build.gradle

    echo -e                                 "create  ${project_name}/src/main/java/${package_path}/debug/DebugActivity.java"
    mkdir -p                                         ${project_name}/src/main/java/${package_path}/debug
    cp ../script/template/DebugActivity.java         ${project_name}/src/main/java/${package_path}/debug/
    sed -i "" s/package_name/${package_name}/g       ${project_name}/src/main/java/${package_path}/debug/DebugActivity.java

    echo -e                             "create  ${project_name}/src/main/res/layout/activity_debug.xml"
    mkdir -p                                     ${project_name}/src/main/res/layout
    cp ../script/template/activity_debug.xml     ${project_name}/src/main/res/layout/
}

create_mediator_ext(){
    echo -e                                           "create  ${project_name}/src/main/AndroidManifest.xml"
    cp ../script/template/mediator_AndroidManifest.xml         ${project_name}/src/main/AndroidManifest.xml
    sed -i "" s/package_name/${package_name}/g                 ${project_name}/src/main/AndroidManifest.xml

    echo -e                                           "create  ${project_name}/src/main/java/${package_path}/IConstant${module_name}.java"
    cp ../script/template/IConstantModule.java                 ${project_name}/src/main/java/${package_path}/IConstant${module_name}.java
    sed -i "" s/package_name/${package_name}/g                 ${project_name}/src/main/java/${package_path}/IConstant${module_name}.java

    echo -e                                           "create  ${project_name}/src/main/java/${package_path}/Mediator${module_name}.java"
    cp ../script/template/MediatorModule.java                  ${project_name}/src/main/java/${package_path}/Mediator${module_name}.java
    sed -i "" s/package_name/${package_name}/g                 ${project_name}/src/main/java/${package_path}/Mediator${module_name}.java

    echo -e                                           "create  ${project_name}/build.gradle"
    cp ../script/template/mediator_build.gradle                ${project_name}/build.gradle
}

create(){
    echo -e "=========create_ing========="
   case $projectType in
        "1")
            project_name="module_"${module_name}
            package_name=${root_package_name}"."${module_name}
            package_path=${package_name//./\/}
            create_project
            create_module_ext
            ;;
        "2")
            project_name="mediator_"${module_name}
            package_name=${root_package_name}".mediator."${module_name}
            package_path=${package_name//./\/}
            create_project
            create_mediator_ext
            ;;
        "3")
            project_name="module_"${module_name}
            package_name=${root_package_name}"."${module_name}
            package_path=${package_name//./\/}
            create_project
            create_module_ext
            project_name="mediator_"${module_name}
            package_name=${root_package_name}".mediator."${module_name}
            package_path=${package_name//./\/}
            create_project
            create_mediator_ext
            ;;
    esac
    echo -e "=========create_end========="
}


while [ "$finish" != "y" -a "$finish" != "Y" ]
do
    if [ "$finish" == "n" -o "$finish" == "N" ]; then
        getInfomation
    fi
    read -p "finish? (y/n):" finish
done