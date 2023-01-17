/* You are given a tree (i.e. a connected, undirected graph that has no cycles) rooted at node 0 consisting of n nodes numbered from 0 to n - 1... The tree is represented by a 0-indexed array parent of size n, where parent[i] is the parent of node i... Since node 0 is the root, parent[0] == -1... You are also given a string s of length n, where s[i] is the character assigned to node i... Return the length of the longest path in the tree such that no pair of adjacent nodes on the path have the same character assigned to them...
 * Eg 1:  parent = [-1,0,0,1,1,2]        s = "abacbe"         Output = 3
 * Eg 2:  parent = [-1,0,0,0]            s = "abbc"           Output = 3
 * Explanation to above case -> {The longest path where each two adjacent nodes have different characters is the path: 2 -> 0 -> 3. The length of this path is 3, so 3 is returned.} 
 */
import java.util.*;
public class Parent
{
    int LongestPath = 1;
    public int MaximumUniqueNode(int parent[], String s)
    {
        Map<Integer, List<Integer>> map = new HashMap<Integer, List<Integer>>();
        for(int i = 0; i < parent.length; i++)
        {
            map.putIfAbsent(parent[i], new ArrayList<Integer>());   // If parent node is not added in map, add the parent node...
            map.get(parent[i]).add(i);     // Now add the children nodes of the current parent...
        }
        System.out.println("HashMap : "+map);
        DepthFirstSearch(0, map, s);     // Calling Depth First Search on the Root Node...
        return LongestPath;
    }
    public int DepthFirstSearch(int node, Map<Integer, List<Integer>> map, String s)
    {
        if(!map.containsKey(node))   // If root node is the leaf node...
            return 1;
        int max1 = 0, max2 = 0;
        for(int n: map.get(node))
        {
            int PathN = DepthFirstSearch(n, map, s);    // Recursive Call for every Node...
            if(s.charAt(node) == s.charAt(n))    // If Neighboring nodes are same we pass...
                continue;
            if(PathN > max1)    // Checking the Left Path...
            {
                max2 = max1;
                max1 = PathN;
            }
            else if(PathN > max2)    // Checking the Right Path... 
                max2 = PathN;
            System.out.println("Node : "+n+" max1 : "+max1+" max2 : "+max2);
        }
        LongestPath = Math.max(LongestPath, max1+max2+1);    // Updating the maximum...
        return max1+1;   // Since root node will itself be considered we update the max...
    }
    public static void main(String[] args)
    {
        Scanner sc = new Scanner(System.in);
        int p;
        String str;
        System.out.print("Enter the number of nodes : ");
        p = sc.nextInt();
        int array[] = new int[p];
        for(int i = 0; i < p; i++)
        {
            System.out.print("Enter the parent of "+(i+1)+" th Node : ");
            array[i] = sc.nextInt();
        }
        System.out.print("Enter the String : ");
        str = sc.next();
        Parent parents = new Parent();   // Object creation...
        System.out.print("The Longest Path is : "+parents.MaximumUniqueNode(array, str));
        sc.close();
    }
}

// Time Complexity  - O(n) time...
// Space Complexity - O(n) space...

/* DEDUCTIONS :- 
 * 1. We Iterate over the Tree using DFS and before that we create an Adjacency list of the Tree with parent as the index and the neighboring Nodes as the List values...
 * 2. Then with Traversal we check for every node and if two adjacent characters are same we do not update the counter and vice versa...
*/