package questions;

import java.util.ArrayList;
import java.util.List;

/**
* 5.说明生活中遇到的二叉树，用 java 实现二叉树
*/
class binaryTree{
    private Object data;    // 需要保存的数据
    private binaryTree left;    // 左孩子
    private binaryTree right;   // 右孩子
    public binaryTree root;    // 根节点
    private List<binaryTree> datas; // 保存节点，大小可变，可使用下标

    public binaryTree(){

    }

    public binaryTree(binaryTree left, binaryTree right, Object data){
        this.left = left;
        this.right = right;
        this.data = data;
    }

    public binaryTree(Object data){ // 数据初始化为左右子数都为空的节点
        this(null, null, data);
    }

    // 创建二叉树
    public void createTree(Object[] objects){   // 以数组数据形式创建二叉树
        datas = new ArrayList<binaryTree>();
        for (Object obj : objects) {    // 将数组数据保存为节点
            datas.add(new binaryTree(obj));
        }
        root = datas.get(0);    // 设置根节点
        for (int i = 0; i < datas.size()/2; i ++){
            datas.get(i).left = datas.get(2*i+1); // 创建左孩子关系
            if (2*i + 2 < datas.size()){    // 防止大小为偶数时出现下标越界的情况
                datas.get(i).right = datas.get(2*i+2);  // 创建右孩子关系
            }
        }
    }

    // 先序遍历
    public void preorder(binaryTree root){
        if (root != null){
            System.out.print(root.data + " ");  // 先输出根节点
            preorder(root.left);    // 递归调用输出左子树
            preorder(root.right);   // 递归调用输出右子树
        }
    }

    // 中序遍历
    public void inorder(binaryTree root){
        if (root != null){
            inorder(root.left);
            System.out.print(root.data + " ");
            inorder(root.right);
        }
    }

    // 后序遍历
    public void postorder(binaryTree root){
        if (root != null){
            postorder(root.left);
            postorder(root.right);
            System.out.print(root.data + "");
        }
    }
}
public class question_5 {
    public static void main(String[] args) {
        Object[] objects = {1,4,5,10,16,6,22,84,45,31,17};
        binaryTree bt = new binaryTree();
        bt.createTree(objects);

        bt.preorder(bt.root);
        System.out.println();

        bt.inorder(bt.root);
        System.out.println();

        bt.postorder(bt.root);
    }
}
