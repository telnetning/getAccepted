"""
# Employee info
class Employee(object):
    def __init__(self, id, importance, subordinates):
        # It's the unique id of each node.
        # unique id of this employee
        self.id = id
        # the importance value of this employee
        self.importance = importance
        # the id of direct subordinates
        self.subordinates = subordinates
"""
class Solution(object):
    def getImportance(self, employees, id):
        """
        :type employees: Employee
        :type id: int
        :rtype: int
        """
        em_dict = {}
        for employee in employees:
            em_dict[employee.id] = employee
        sum = 0

        def get_sum(id):
            sum = em_dict[id].importance
            for i in em_dict[id].subordinates:
                sum += get_sum(i)
            return sum

        sum += get_sum(id)

        return sum