package skill;

public class Test7_BinaryTree {
    Node head=null;
    public class Node{
        Node left;
        Node right;
        int value;
        Node (int data){
            this.value=data;
            left=null;
            right=null;
        }
    }//node
    public boolean insertNode(int data){
        //1.노드가하나도없을때
        if(this.head==null){
            head=new Node(data);
            return true;
        }
        //2.노드가 하나이상 들어가있을때
        Node findNode = this.head;
        while(true){
            //2-1:노드를 왼쪽에 추가해야할때
            if(data<findNode.value){//2-1
                if(findNode.left!=null){
                    findNode=findNode.left;
                }
                else{
                    findNode.left=new Node(data);
                    break;
                }
            }//2-1
            //2-2:노드를 오른쪽에 추가해야할때
            else{//2-2
                if(findNode.right!=null){
                    findNode=findNode.right;
                }
                else{
                    findNode.right=new Node(data);
                    break;
                }
            }//2-2
        }
        return true;
    }

    public Node search(int data){
        //1.노드가 하나도 없을때
        if(head==null){
            return null;
        }
        //2.노드가 하나이상 있을때
        else{
            Node findNode=head;
            while(findNode!=null){
                if(findNode.value==data){
                    return findNode;
                }
                else if(data<findNode.value){
                    findNode=findNode.left;
                }
                else if(data>findNode.value){
                    findNode=findNode.right;
                }
            }//while
        }
        return null;
    }

    public boolean delete(int value) {
        boolean searched = false;

        Node currParentNode = this.head;
        Node currNode = this.head;

        //코너케이스1.노드가 하나도 없을때
        if (head == null) {
            return false;
        }
        //코너케이스2.노드(헤드)가 하나만있고 해당 노드가 삭제할 노드일때
        else {
            if (this.head.value == value && this.head.left == null && this.head.right == null) {
                head = null;
                return true;
            }

        }//else
        //코너케이스3.찾을 노드가 null일때 + null이 아니면 찾는노드 선택
        while (currNode != null) {
            if (currNode.value == value) {
                searched = true;
                break;
            } else if (value < currNode.value) {
                currParentNode = currNode;
                currNode = currNode.left;
            } else {
                currParentNode = currNode;
                currNode = currNode.right;
            }
        }//while
        //삭제할 노드가 null인경우도 거름
        if (searched == false) {
            return false;
        }

        //삭제할노드가 있는경우
        //case1:삭제할 node가 leaf node인경우
        if (currNode.left == null && currNode.right == null) {
            if (value < currParentNode.value) {
                currParentNode.left = null;
                currNode = null;
            } else {
                currParentNode.right = null;
                currNode = null;
            }
            return true;
        }//if
        //case2:삭제할 node가 자식을 하나 가지고있을경우
        else if (currNode.left != null && currNode.right == null) {
            if (currNode.value > currParentNode.value) {
                currParentNode.right = currNode.left;
                currNode = null;
            } else {
                currParentNode.left = currNode.left;
                currNode = null;
            }
            return true;
        } else if (currNode.left == null && currNode.right != null) {
            if (currNode.value > currParentNode.value) {
                currParentNode.right = currNode.right;
                currNode = null;
            } else {
                currParentNode.left = currNode.right;
                currNode = null;
            }
            return true;
        }

        //case3:삭제할 node가 자식을 두개 가지고 있을경우
        else {
            //case3-1:삭제할node가 부모의 왼쪽일때
            if (currNode.value < currParentNode.value) {
                Node changeNode = currNode.right;
                Node changeParentNode = currNode.right;
                while (changeNode.left != null) {
                    changeParentNode = changeNode;
                    changeNode = changeNode.left;
                }
                //changenode가 가장 작은수가 되었다
                //그러므로 changenode의 자식이 있으면 오른쪽만 가능
                if (changeNode.right == null) {
                    changeParentNode.left = null;
                } else {
                    changeParentNode.left = changeNode.right;
                }
                changeNode.left = currNode.left;
                changeNode.right = currNode.right;
                currParentNode.left = changeNode;
                currNode = null;
            }//if case3-1
            //case3-2:삭제할node가 부모의 오른쪽일때
            else {
                Node changeNode = currNode.right;
                Node changeParentNode = currNode.right;
                while (changeNode.left != null) {
                    changeParentNode = changeNode;
                    changeNode = changeNode.left;
                }
                //changenode가 가장 작은수가 되었다
                //그러므로 changenode의 자식이 있으면 오른쪽만 가능
                if (changeNode.right == null) {
                    changeParentNode.left = null;
                } else {
                    changeParentNode.left = changeNode.right;
                }
                changeNode.left = currNode.left;
                changeNode.right = currNode.right;
                currParentNode.right = changeNode;
                currNode = null;
            }
        }//else외부
        return true;
    }
}
