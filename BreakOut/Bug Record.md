## Bug Record
This is the bug record based on the test time.
Bugs found are recorded for later maintenance and modification.

<table>
<tr>
    <th>Test Date</th>
    <td>12/11/2021</td>
</tr>
<tr>
    <th>Class</th>
    <td>StartGame</td>
</tr>
<tr>
    <th>Method</th>
    <td>main</td>
</tr>
<tr>
    <th rowspan = "3">Bug Description</th>
    <td>1. The ball touched the far right edge of the interface and plummeted, ignoring the board. After the ball disappears from the interface, it does not appear again and the interface displays: Focus Lost</td>
</tr>
<tr>
    <td>2. After all the levels have been passed, the "All Wells Destroyed" appears without telling the user exactly what to do next</td>
</tr>
<tr>
    <td>3. Use debug panel to reset the ball, and ball began to move horizontally.<br/> Error massage shows: <br/> 
        <i>java[50916:5815679] TSM AdjustCapsLockLEDForKeyTransitionHandling - _ISSetPhysicalKeyboardCapsLockLED Inhibit</i></td>
</tr>
</table>

<table>
<tr>
    <th>Test Date</th>
    <td>23/11/2021</td>
</tr>
<tr>
    <th>Class</th>
    <td>GameFrame</td>
</tr>
<tr>
    <th>Member variables</th>
    <td> public static final String DEF_TITLE</td>
</tr>
<tr>
    <th >Bug Description</th>
    <td>1. Works more like the user guide than the title.</td>
</tr>
</table>